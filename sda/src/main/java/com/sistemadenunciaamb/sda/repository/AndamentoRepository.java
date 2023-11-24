package com.sistemadenunciaamb.sda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemadenunciaamb.sda.models.Andamento;

@Repository
public interface AndamentoRepository extends JpaRepository<Andamento, Integer> {
}
