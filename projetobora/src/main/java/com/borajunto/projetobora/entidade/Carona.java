package com.borajunto.projetobora.entidade;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "BJT_CARONA")
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

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getMotorista() {
        return motorista;
    }

    public void setMotorista(Usuario motorista) {
        this.motorista = motorista;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDateTime getDataHoraSaida() {
        return dataHoraSaida;
    }

    public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public Integer getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public void setVagasDisponiveis(Integer vagasDisponiveis) {
        this.vagasDisponiveis = vagasDisponiveis;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusCarona getStatus() {
        return status;
    }

    public void setStatus(StatusCarona status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public List<Passageiro> getPassageiros() {
        return passageiros;
    }

    public void setPassageiros(List<Passageiro> passageiros) {
        this.passageiros = passageiros;
    }
}
