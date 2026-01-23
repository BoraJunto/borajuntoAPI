package com.borajunto.projetobora.dto;

import java.time.LocalDateTime;

import com.borajunto.projetobora.entidade.Passageiro;
import com.borajunto.projetobora.entidade.StatusPassageiro;

public class PassageiroDTO {
    private Long id;
    private Long caronaId;
    private String cpfUsuario;
    private String nomeUsuario;
    private StatusPassageiro status;
    private LocalDateTime dataRequisicao;
    private LocalDateTime dataAprovacao;
    private String observacoes;

    public PassageiroDTO() {
    }

    public PassageiroDTO(Passageiro passageiro) {
        this.id = passageiro.getId();
        this.caronaId = passageiro.getCarona().getId();
        this.cpfUsuario = passageiro.getUsuario().getCpf();
        this.nomeUsuario = passageiro.getUsuario().getNome();
        this.status = passageiro.getStatus();
        this.dataRequisicao = passageiro.getDataRequisicao();
        this.dataAprovacao = passageiro.getDataAprovacao();
        this.observacoes = passageiro.getObservacoes();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCaronaId() {
        return caronaId;
    }

    public void setCaronaId(Long caronaId) {
        this.caronaId = caronaId;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
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
