package com.borajunto.projetobora.dto;

import java.time.LocalDateTime;

import com.borajunto.projetobora.entidade.Passageiro;
import com.borajunto.projetobora.entidade.StatusPassageiro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassageiroDTO {
    private Long id;
    private Long caronaId;
    private String cpfUsuario;
    private String nomeUsuario;
    private StatusPassageiro status;
    private LocalDateTime dataRequisicao;
    private LocalDateTime dataAprovacao;
    private String observacoes;

    public PassageiroDTO() {}

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
}
