package com.borajunto.projetobora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.borajunto.projetobora.entidade.Passageiro;

public interface PassageiroRepository extends JpaRepository<Passageiro, Integer>{
    
}
