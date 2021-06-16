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

    private CartaoDeCredito save(CartaoDeCredito cartaoDeCredito) {
        return cartaoDeCreditoRepository.save(cartaoDeCredito);
    }

    private BigInteger defineLimite(Short score) {
        return BigInteger.valueOf(123L);
    }

    public CartaoDeCreditoDTO criarParaConta(ContaDTO contaDTO, Short score) {

        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito();
        cartaoDeCredito.setConta(Conta.create(contaDTO));
        cartaoDeCredito.setLimiteEmCentavos(defineLimite(score));
        
        return CartaoDeCreditoDTO.create(save(cartaoDeCredito));
    }
}
