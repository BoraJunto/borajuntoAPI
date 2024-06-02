package com.borajunto.projetobora.entidade;

import java.util.Date;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor

public class Usuario {

    @NotBlank
    @Email
    
    private String email;

    @NotBlank
    @CPF(message = "Esse CPF é inválido.")
    private String cpf;

    @NotBlank
    private String ra;

    @NotBlank
    private String universidade;

    @NotBlank
    private String nome;

    @NotBlank
    private String senha;

    @NotNull
    private Date nascimento; 

    @NotBlank
    private String sexo;

    @NotBlank
    private String telefone;

    @NotBlank
    private String ddd;

}
