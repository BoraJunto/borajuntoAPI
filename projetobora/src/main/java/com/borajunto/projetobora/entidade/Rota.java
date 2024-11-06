package com.borajunto.projetobora.entidade;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "rotas")
@Getter
@Setter
@NoArgsConstructor
public class Rota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rotaid;
    @NotBlank
    private String origem;
    @NotBlank
    private String destino;
    @NotBlank
    private double distancia;
    @NotBlank
    private double duracao;
    
}
