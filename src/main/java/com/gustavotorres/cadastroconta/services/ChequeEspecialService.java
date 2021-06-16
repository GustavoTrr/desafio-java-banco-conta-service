package com.gustavotorres.cadastroconta.services;

import java.math.BigInteger;

import com.gustavotorres.cadastroconta.dtos.ChequeEspecialDTO;
import com.gustavotorres.cadastroconta.dtos.ContaDTO;
import com.gustavotorres.cadastroconta.entities.ChequeEspecial;
import com.gustavotorres.cadastroconta.entities.Conta;
import com.gustavotorres.cadastroconta.repositories.ChequeEspecialRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChequeEspecialService {
    
    @Autowired
    private ChequeEspecialRepository chequeEspecialRepository;

    private ChequeEspecial save(ChequeEspecial chequeEspecial) {
        return chequeEspecialRepository.save(chequeEspecial);
    }

    private BigInteger defineLimite(Short score) {
        return BigInteger.valueOf(123L);
    }

    public ChequeEspecialDTO criarParaConta(ContaDTO contaDTO, Short score) {
        
        ChequeEspecial chequeEspecial = new ChequeEspecial();
        chequeEspecial.setConta(Conta.create(contaDTO));
        chequeEspecial.setLimiteEmCentavos(defineLimite(score));
        
        return ChequeEspecialDTO.create(save(chequeEspecial));
    }
}
