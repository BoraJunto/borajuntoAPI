package com.borajunto.projetobora.dto;

import java.time.LocalDateTime;

import com.borajunto.projetobora.entidade.Carona;
import com.borajunto.projetobora.entidade.StatusCarona;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public CaronaDTO() {}

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
}
