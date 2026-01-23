package com.borajunto.projetobora.entidade;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "BJT_CARONA")
@Getter
@Setter
public class Carona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cpf_motorista", nullable = false)
    private Usuario motorista;

    @Column(nullable = false)
    private String origem;

    @Column(nullable = false)
    private String destino;

    @Column(nullable = false)
    private LocalDateTime dataHoraSaida;

    @Column(nullable = false)
    private Integer vagas;

    @Column(nullable = true)
    private Integer vagasDisponiveis;

    @Column(nullable = true)
    private Double valor;

    @Column(nullable = true)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCarona status;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = true)
    private LocalDateTime dataAtualizacao;

    @OneToMany(mappedBy = "carona", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Passageiro> passageiros;

    public Carona() {
        this.dataCriacao = LocalDateTime.now();
        this.status = StatusCarona.ATIVA;
    }

    public Carona(Usuario motorista, String origem, String destino, 
                  LocalDateTime dataHoraSaida, Integer vagas, Double valor) {
        this.motorista = motorista;
        this.origem = origem;
        this.destino = destino;
        this.dataHoraSaida = dataHoraSaida;
        this.vagas = vagas;
        this.vagasDisponiveis = vagas;
        this.valor = valor;
        this.dataCriacao = LocalDateTime.now();
        this.status = StatusCarona.ATIVA;
    }
}
