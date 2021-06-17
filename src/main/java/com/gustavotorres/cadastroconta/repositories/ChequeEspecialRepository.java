package com.gustavotorres.cadastroconta.repositories;

import com.gustavotorres.cadastroconta.entities.ChequeEspecial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChequeEspecialRepository extends JpaRepository<ChequeEspecial, Long> {
    
}
