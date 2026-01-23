package com.borajunto.projetobora.entidade;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "BJT_PASSAGEIRO")
@Getter
@Setter
public class Passageiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carona_id", nullable = false)
    private Carona carona;

    @ManyToOne
    @JoinColumn(name = "cpf_usuario", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPassageiro status;

    @Column(nullable = false)
    private LocalDateTime dataRequisicao;

    @Column(nullable = true)
    private LocalDateTime dataAprovacao;

    @Column(nullable = true)
    private String observacoes;

    public Passageiro() {
        this.dataRequisicao = LocalDateTime.now();
        this.status = StatusPassageiro.PENDENTE;
    }

    public Passageiro(Carona carona, Usuario usuario) {
        this.carona = carona;
        this.usuario = usuario;
        this.dataRequisicao = LocalDateTime.now();
        this.status = StatusPassageiro.PENDENTE;
    }
}
