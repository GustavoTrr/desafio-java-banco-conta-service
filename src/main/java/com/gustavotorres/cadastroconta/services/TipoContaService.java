package com.gustavotorres.cadastroconta.services;

import javax.validation.ValidationException;

import com.gustavotorres.cadastroconta.enums.TipoConta;
import com.gustavotorres.cadastroconta.enums.TipoPessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoContaService {

    @Autowired
    TipoPessoaService tipoPessoaService;
    
    public TipoConta definirTipoContaPorTipoPesoa(String strTipoPessoa) {
        if (TipoPessoa.PF.name().equals(strTipoPessoa)) {
            return TipoConta.C;
        } else if (TipoPessoa.PJ.name().equals(strTipoPessoa)) {
            return TipoConta.E;
        } else {
            throw new ValidationException("Tipo de Pessoa não aceito!");
        }
    } 
}
