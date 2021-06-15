package com.gustavotorres.cadastroconta.services;

import com.gustavotorres.cadastroconta.entities.ChequeEspecial;
import com.gustavotorres.cadastroconta.repositories.ChequeEspecialRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChequeEspecialService {
    
    @Autowired
    private ChequeEspecialRepository chequeEspecialRepository;

    public ChequeEspecial save(ChequeEspecial chequeEspecial) {
        return chequeEspecialRepository.save(chequeEspecial);
    }
}
