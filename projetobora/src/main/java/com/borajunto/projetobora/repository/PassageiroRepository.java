package com.borajunto.projetobora.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.borajunto.projetobora.entidade.Passageiro;


public interface PassageiroRepository extends JpaRepository<Passageiro, Integer>{
    boolean existsByEmail(String email);
    
    Optional<Passageiro> findByEmail(String email);
}
