package com.gustavotorres.cadastroconta.services;

import com.gustavotorres.cadastroconta.entities.CartaoDeCredito;
import com.gustavotorres.cadastroconta.repositories.CartaoDeCreditoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoDeCreditoService {
    
    @Autowired
    private CartaoDeCreditoRepository cartaoDeCreditoRepository;

    public CartaoDeCredito save(CartaoDeCredito cartaoDeCredito) {
        return cartaoDeCreditoRepository.save(cartaoDeCredito);
    }
}
