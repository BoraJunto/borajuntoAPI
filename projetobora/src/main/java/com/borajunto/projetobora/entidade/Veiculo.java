package com.borajunto.projetobora.entidade;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "veiculo")
@Getter
@Setter
@NoArgsConstructor
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int passageiro_id;
    @NotBlank
    private String modelo;
    @NotBlank
    private String marca;
    @NotBlank
    private int ano;
    @NotBlank
    private String placa;
    @NotBlank
    private String cor;

    
}
