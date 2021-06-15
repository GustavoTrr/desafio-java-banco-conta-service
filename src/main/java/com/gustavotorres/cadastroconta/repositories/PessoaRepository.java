package com.gustavotorres.cadastroconta.repositories;

import com.gustavotorres.cadastroconta.entities.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    
}
