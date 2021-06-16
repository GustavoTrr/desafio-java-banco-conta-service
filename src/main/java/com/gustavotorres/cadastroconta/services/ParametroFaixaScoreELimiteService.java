package com.gustavotorres.cadastroconta.services;

import java.math.BigInteger;

import com.gustavotorres.cadastroconta.enums.Score;
import com.gustavotorres.cadastroconta.repositories.ParametroFaixaScoreELimiteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParametroFaixaScoreELimiteService {

    @Autowired
    ParametroFaixaScoreELimiteRepository parametroFaixaScoreELimiteRepository;
    
    public BigInteger getLimiteCartaoPorScore(Short score) {
        return parametroFaixaScoreELimiteRepository.findLimiteCartaoEmCentavosByScore(score);
    }

    public BigInteger getLimiteChequeEspecialPorScore(Short score) {
        return parametroFaixaScoreELimiteRepository.findLimiteChequeEspecialEmCentavosByScore(score);
    }
}
