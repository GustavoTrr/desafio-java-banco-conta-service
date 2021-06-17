package com.gustavotorres.cadastroconta.services;

import java.math.BigInteger;

import com.gustavotorres.cadastroconta.dtos.CartaoDeCreditoDTO;
import com.gustavotorres.cadastroconta.dtos.ContaDTO;
import com.gustavotorres.cadastroconta.entities.CartaoDeCredito;
import com.gustavotorres.cadastroconta.entities.Conta;
import com.gustavotorres.cadastroconta.repositories.CartaoDeCreditoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoDeCreditoService {
    
    @Autowired
    private CartaoDeCreditoRepository cartaoDeCreditoRepository;

    @Autowired
    private ParametroFaixaScoreELimiteService parametroFaixaScoreELimiteService;

    private CartaoDeCredito save(CartaoDeCredito cartaoDeCredito) {
        return cartaoDeCreditoRepository.save(cartaoDeCredito);
    }

    private BigInteger defineLimite(Short score) {
        return parametroFaixaScoreELimiteService.getLimiteCartaoPorScore(score);
    }

    public CartaoDeCreditoDTO criarParaConta(ContaDTO contaDTO, Short score) {

        var limite = defineLimite(score);

        if (limite.equals(BigInteger.ZERO)) {
            return null;
        }

        CartaoDeCredito cartaoDeCredito = CartaoDeCredito.builder()
            .conta(Conta.create(contaDTO))
            .limiteEmCentavos(limite)
            .build();
        
        return CartaoDeCreditoDTO.create(save(cartaoDeCredito));
    }
}
