package com.gustavotorres.cadastroconta.repositories;

import com.gustavotorres.cadastroconta.entities.CartaoDeCredito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoDeCreditoRepository extends JpaRepository<CartaoDeCredito, Long> {

}
