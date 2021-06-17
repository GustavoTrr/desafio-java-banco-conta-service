package com.gustavotorres.cadastroconta.services;

import com.gustavotorres.cadastroconta.dtos.PessoaCadastroInputDTO;
import com.gustavotorres.cadastroconta.dtos.PessoaDTO;
import com.gustavotorres.cadastroconta.entities.Pessoa;
import com.gustavotorres.cadastroconta.repositories.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public boolean isIdPublicoCadastrado(String idPublico) {
        return pessoaRepository.existsPessoaByIdPublico(idPublico);
    }

    public PessoaDTO cadastrarPessoa(PessoaCadastroInputDTO pessoaCadastroDTO) {
        return PessoaDTO.create(pessoaRepository.save(Pessoa.create(pessoaCadastroDTO)));
    }
}
