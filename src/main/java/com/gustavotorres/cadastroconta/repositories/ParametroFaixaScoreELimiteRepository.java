package com.gustavotorres.cadastroconta.repositories;

import java.math.BigInteger;

import com.gustavotorres.cadastroconta.entities.ParametroFaixaScoreELimite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroFaixaScoreELimiteRepository extends JpaRepository<ParametroFaixaScoreELimite, Long> {
    
    public BigInteger findLimiteCartaoEmCentavosByScore(Short score);
    public BigInteger findLimiteChequeEspecialEmCentavosByScore(Short score);
}
