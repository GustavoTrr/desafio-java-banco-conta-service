package com.gustavotorres.cadastroconta.eventlistaners;

import java.math.BigInteger;
import java.util.Arrays;

import javax.transaction.Transactional;

import com.gustavotorres.cadastroconta.entities.ParametroFaixaScoreELimite;
import com.gustavotorres.cadastroconta.repositories.ParametroFaixaScoreELimiteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Seeder {

    @Autowired
    ParametroFaixaScoreELimiteRepository parametroFaixaScoreELimiteRepository;
    
    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedParametroFaixaScoreELimiteTable();
    }

    @Transactional
    private void seedParametroFaixaScoreELimiteTable() {

        if (parametroFaixaScoreELimiteRepository.findAll().isEmpty()){
            
            System.out.println(" >> Iniciando o seed da tabela parametros_faixa_score_e_limites");

            parametroFaixaScoreELimiteRepository.saveAll(Arrays.asList(
                ParametroFaixaScoreELimite.builder().score(Short.valueOf("0")).limiteCartaoEmCentavos(BigInteger.valueOf(0L)).limiteChequeEspecialEmCentavos(BigInteger.valueOf(0L)).build(),
                ParametroFaixaScoreELimite.builder().score(Short.valueOf("1")).limiteCartaoEmCentavos(BigInteger.valueOf(0L)).limiteChequeEspecialEmCentavos(BigInteger.valueOf(0L)).build(),
                ParametroFaixaScoreELimite.builder().score(Short.valueOf("2")).limiteCartaoEmCentavos(BigInteger.valueOf(20000L)).limiteChequeEspecialEmCentavos(BigInteger.valueOf(100000L)).build(),
                ParametroFaixaScoreELimite.builder().score(Short.valueOf("3")).limiteCartaoEmCentavos(BigInteger.valueOf(20000L)).limiteChequeEspecialEmCentavos(BigInteger.valueOf(100000L)).build(),
                ParametroFaixaScoreELimite.builder().score(Short.valueOf("4")).limiteCartaoEmCentavos(BigInteger.valueOf(20000L)).limiteChequeEspecialEmCentavos(BigInteger.valueOf(100000L)).build(),
                ParametroFaixaScoreELimite.builder().score(Short.valueOf("5")).limiteCartaoEmCentavos(BigInteger.valueOf(20000L)).limiteChequeEspecialEmCentavos(BigInteger.valueOf(100000L)).build(),
                ParametroFaixaScoreELimite.builder().score(Short.valueOf("6")).limiteCartaoEmCentavos(BigInteger.valueOf(200000L)).limiteChequeEspecialEmCentavos(BigInteger.valueOf(200000L)).build(),
                ParametroFaixaScoreELimite.builder().score(Short.valueOf("7")).limiteCartaoEmCentavos(BigInteger.valueOf(200000L)).limiteChequeEspecialEmCentavos(BigInteger.valueOf(200000L)).build(),
                ParametroFaixaScoreELimite.builder().score(Short.valueOf("8")).limiteCartaoEmCentavos(BigInteger.valueOf(200000L)).limiteChequeEspecialEmCentavos(BigInteger.valueOf(200000L)).build(),
                ParametroFaixaScoreELimite.builder().score(Short.valueOf("9")).limiteCartaoEmCentavos(BigInteger.valueOf(1500000L)).limiteChequeEspecialEmCentavos(BigInteger.valueOf(500000L)).build()
            ));

            System.out.println(" >> Finalizado o seed da tabela parametros_faixa_score_e_limites");
        }
    }
}
