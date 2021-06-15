package com.gustavotorres.cadastroconta.repositories;

import com.gustavotorres.cadastroconta.entities.Conta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    
}
