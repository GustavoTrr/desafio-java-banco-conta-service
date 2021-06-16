package com.gustavotorres.cadastroconta.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import com.gustavotorres.cadastroconta.LimiteDeContasException;
import com.gustavotorres.cadastroconta.dtos.CartaoDeCreditoDTO;
import com.gustavotorres.cadastroconta.dtos.ChequeEspecialDTO;
import com.gustavotorres.cadastroconta.dtos.ContaDTO;
import com.gustavotorres.cadastroconta.dtos.PessoaCadastroInputDTO;
import com.gustavotorres.cadastroconta.dtos.PessoaDTO;
import com.gustavotorres.cadastroconta.entities.Conta;
import com.gustavotorres.cadastroconta.enums.TipoConta;
import com.gustavotorres.cadastroconta.repositories.ContaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
    
    @Autowired ContaRepository contaRepository;
    @Autowired PessoaService pessoaService;
    @Autowired ChequeEspecialService chequeEspecialService;
    @Autowired CartaoDeCreditoService cartaoDeCreditoService;
    @Autowired TipoPessoaService tipoPessoaService;
    @Autowired TipoContaService tipoContaService;

    @Value("${constants.conta.agencia}")
    private String agencia;

    @Transactional
    public ContaDTO criarConta(PessoaCadastroInputDTO pessoaCadastroDTO) {
        
        validarPessoaParaCadastro(pessoaCadastroDTO);
        
        PessoaDTO pessoaDTO = pessoaService.cadastrarPessoa(pessoaCadastroDTO);
        
        ContaDTO contaDTOParaCriar = prepararContaParaCriar(pessoaDTO, pessoaCadastroDTO.getTipoPessoa());
        ContaDTO contaDTOCriada = save(contaDTOParaCriar);
        
        CartaoDeCreditoDTO cartaoDeCredito = cartaoDeCreditoService.criarParaConta(contaDTOCriada, pessoaCadastroDTO.getScore());
        ChequeEspecialDTO chequeEspecial = chequeEspecialService.criarParaConta(contaDTOCriada, pessoaCadastroDTO.getScore());


        contaDTOCriada.setCartaoDeCredito(Arrays.asList(cartaoDeCredito));
        contaDTOCriada.setChequeEspecial(chequeEspecial);

        return contaDTOCriada;
    }

    private ContaDTO save(ContaDTO contaDTOParaCriar) {
        return ContaDTO.create( contaRepository.save(Conta.create(contaDTOParaCriar)) );
    }

    private ContaDTO prepararContaParaCriar(PessoaDTO pessoaDTO, String tipoPessoa) {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setAgencia(getAgencia());
        contaDTO.setNumeroConta(getNumeroNovaConta());
        contaDTO.setTipoConta(getTipoConta(tipoPessoa));
        contaDTO.setPessoa(pessoaDTO);

        return contaDTO;
    }

    private String getNumeroNovaConta() {

        Long maxId = contaRepository.getMaxId();
        maxId = maxId == null ? 0 : maxId;
        if (maxId > 999999) {
            throw new LimiteDeContasException("Chegamos ao limite de número de conta de 6 dígitos!");
        }

        Long numConta = maxId + 1;
        
        String strNumConta = String.format("%6s", String.valueOf(numConta)).replace(' ', '0');
        return strNumConta;
    }

    private String getAgencia() {
        return this.agencia;
    }

    private TipoConta getTipoConta(String tipoPessoa) {
        return tipoContaService.definirTipoContaPorTipoPesoa(tipoPessoa);
    }

    public void validarPessoaParaCadastro(PessoaCadastroInputDTO pessoaCadastroDTO) {

        if (!tipoPessoaService.validarTipoPessoa(pessoaCadastroDTO.getTipoPessoa())) {
            throw new ValidationException("TipoPessoa inválido.");
        }

        if (pessoaService.isIdPublicoCadastrado(pessoaCadastroDTO.getIdPublico())) {
            throw new ValidationException("IdPublico já cadastrado.");
        }

    }

    public List<ContaDTO> findAll() {
        return contaRepository.findAll().stream().map(ContaDTO::create).collect(Collectors.toList());
    }

    public Page<ContaDTO> findAll(Pageable pageable) {
        var page = contaRepository.findAll(pageable);
        return page.map(ContaDTO::create);
    }

    
}
