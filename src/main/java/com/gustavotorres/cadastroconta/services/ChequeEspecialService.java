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

    @Autowired
    private ParametroFaixaScoreELimiteService parametroFaixaScoreELimiteService;

    private ChequeEspecial save(ChequeEspecial chequeEspecial) {
        return chequeEspecialRepository.save(chequeEspecial);
    }

    private BigInteger defineLimite(Short score) {
        return parametroFaixaScoreELimiteService.getLimiteChequeEspecialPorScore(score);
    }

    public ChequeEspecialDTO criarParaConta(ContaDTO contaDTO, Short score) {
        
        var limite = defineLimite(score);
        
        ChequeEspecial chequeEspecial = ChequeEspecial.builder()
            .conta(Conta.create(contaDTO))
            .limiteEmCentavos(limite)
            .build();
        
        return ChequeEspecialDTO.create(save(chequeEspecial));
    }
}
