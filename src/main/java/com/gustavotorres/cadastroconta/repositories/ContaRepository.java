package com.gustavotorres.cadastroconta.repositories;

import java.util.List;

import com.gustavotorres.cadastroconta.entities.Conta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    
    @Query(value = "SELECT MAX(id) FROM contas", nativeQuery = true)
	public Long getMaxId();
}
