package com.gustavotorres.cadastroconta.services;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import com.gustavotorres.cadastroconta.dtos.ContaDTO;
import com.gustavotorres.cadastroconta.dtos.PessoaCadastroInputDTO;
import com.gustavotorres.cadastroconta.entities.CartaoDeCredito;
import com.gustavotorres.cadastroconta.entities.ChequeEspecial;
import com.gustavotorres.cadastroconta.entities.Conta;
import com.gustavotorres.cadastroconta.entities.Pessoa;
import com.gustavotorres.cadastroconta.enums.TipoConta;
import com.gustavotorres.cadastroconta.repositories.ContaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
    
    @Autowired ContaRepository contaRepository;
    @Autowired PessoaService pessoaService;
    @Autowired ChequeEspecialService chequeEspecialService;
    @Autowired CartaoDeCreditoService cartaoDeCreditoService;

    public ContaDTO criarConta(PessoaCadastroInputDTO pessoaCadastroDTO) {
        
        Pessoa pessoa = new Pessoa();
        pessoa.setIdPublico("idPublico3");
        pessoaService.save(pessoa);

        Conta conta = new Conta();
        conta.setAgencia("agencia3");
        conta.setNumeroConta(Short.valueOf("1233"));
        conta.setPessoa(pessoa);
        conta.setTipoConta(TipoConta.C);
        contaRepository.save(conta);

        CartaoDeCredito cartao = new CartaoDeCredito();
        cartao.setLimiteEmCentavos(BigInteger.valueOf(1234335L));
        cartao.setConta(conta);
        cartaoDeCreditoService.save(cartao);

        ChequeEspecial cheque = new ChequeEspecial();
        cheque.setLimiteEmCentavos(BigInteger.valueOf(1234335L));
        cheque.setConta(conta);
        chequeEspecialService.save(cheque);

        return ContaDTO.create(conta);
        
    }

    public List<ContaDTO> findAll() {
        return contaRepository.findAll().stream().map(ContaDTO::create).collect(Collectors.toList());
    }

    
}
