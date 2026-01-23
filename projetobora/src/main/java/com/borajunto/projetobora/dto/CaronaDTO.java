package com.borajunto.projetobora.dto;

import java.time.LocalDateTime;

import com.borajunto.projetobora.entidade.Carona;
import com.borajunto.projetobora.entidade.StatusCarona;

public class CaronaDTO {
    private Long id;
    private String cpfMotorista;
    private String nomeMotorista;
    private String origem;
    private String destino;
    private LocalDateTime dataHoraSaida;
    private Integer vagas;
    private Integer vagasDisponiveis;
    private Double valor;
    private String descricao;
    private StatusCarona status;
    private LocalDateTime dataCriacao;

    public CaronaDTO() {
    }

    public CaronaDTO(Carona carona) {
        this.id = carona.getId();
        this.cpfMotorista = carona.getMotorista().getCpf();
        this.nomeMotorista = carona.getMotorista().getNome();
        this.origem = carona.getOrigem();
        this.destino = carona.getDestino();
        this.dataHoraSaida = carona.getDataHoraSaida();
        this.vagas = carona.getVagas();
        this.vagasDisponiveis = carona.getVagasDisponiveis();
        this.valor = carona.getValor();
        this.descricao = carona.getDescricao();
        this.status = carona.getStatus();
        this.dataCriacao = carona.getDataCriacao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfMotorista() {
        return cpfMotorista;
    }

    public void setCpfMotorista(String cpfMotorista) {
        this.cpfMotorista = cpfMotorista;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
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
}
