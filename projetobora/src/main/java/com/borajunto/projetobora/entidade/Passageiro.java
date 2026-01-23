package com.borajunto.projetobora.entidade;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "BJT_PASSAGEIRO")
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

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Carona getCarona() {
        return carona;
    }

    public void setCarona(Carona carona) {
        this.carona = carona;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public StatusPassageiro getStatus() {
        return status;
    }

    public void setStatus(StatusPassageiro status) {
        this.status = status;
    }

    public LocalDateTime getDataRequisicao() {
        return dataRequisicao;
    }

    public void setDataRequisicao(LocalDateTime dataRequisicao) {
        this.dataRequisicao = dataRequisicao;
    }

    public LocalDateTime getDataAprovacao() {
        return dataAprovacao;
    }

    public void setDataAprovacao(LocalDateTime dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
