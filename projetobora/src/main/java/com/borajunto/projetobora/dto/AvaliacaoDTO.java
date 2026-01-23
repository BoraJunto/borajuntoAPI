package com.borajunto.projetobora.dto;

import java.time.LocalDateTime;

import com.borajunto.projetobora.entidade.Avaliacao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvaliacaoDTO {
    private Long id;
    private Long caronaId;
    private String cpfAvaliador;
    private String nomeAvaliador;
    private String cpfAvaliado;
    private String nomeAvaliado;
    private Integer nota;
    private String comentario;
    private LocalDateTime dataAvaliacao;

    public AvaliacaoDTO() {}

    public AvaliacaoDTO(Avaliacao avaliacao) {
        this.id = avaliacao.getId();
        this.caronaId = avaliacao.getCarona().getId();
        this.cpfAvaliador = avaliacao.getAvaliador().getCpf();
        this.nomeAvaliador = avaliacao.getAvaliador().getNome();
        this.cpfAvaliado = avaliacao.getAvaliado().getCpf();
        this.nomeAvaliado = avaliacao.getAvaliado().getNome();
        this.nota = avaliacao.getNota();
        this.comentario = avaliacao.getComentario();
        this.dataAvaliacao = avaliacao.getDataAvaliacao();
    }
}
