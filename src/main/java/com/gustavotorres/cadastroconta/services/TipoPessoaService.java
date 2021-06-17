package com.gustavotorres.cadastroconta.services;

import com.gustavotorres.cadastroconta.enums.TipoPessoa;

import org.springframework.stereotype.Service;

@Service
public class TipoPessoaService {
    
    public boolean validarTipoPessoa(String strTipoPessoaInformado) {
        for (TipoPessoa tipo : TipoPessoa.values()) {
            if (tipo.name().equals(strTipoPessoaInformado)) {
                return true;
            }
        }
        return false;
    }
}
