package com.borajunto.projetobora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.borajunto.projetobora.entidade.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    
}
