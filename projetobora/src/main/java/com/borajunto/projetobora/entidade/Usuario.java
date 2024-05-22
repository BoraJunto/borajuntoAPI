package com.borajunto.projetobora.entidade;

import java.sql.Date;


import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor

public class Usuario {

    
    private String email;
    private String cpf;
    private String ra;
    private String universidade;
    private String nome;
    private String senha;
    private Date nascimento; 
    private String sexo;

}
