package com.borajunto.projetobora.entidade;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "motorista")

@Getter
@Setter
@NoArgsConstructor
public class Motorista extends Usuario{

    @Id
    private int id;
    
    private String cnh;
}
