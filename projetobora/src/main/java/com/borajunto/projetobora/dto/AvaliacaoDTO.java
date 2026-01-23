package com.borajunto.projetobora.dto;

import java.time.LocalDateTime;

import com.borajunto.projetobora.entidade.Avaliacao;

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

    public AvaliacaoDTO() {
    }

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

    public String getCpfAvaliador() {
        return cpfAvaliador;
    }

    public void setCpfAvaliador(String cpfAvaliador) {
        this.cpfAvaliador = cpfAvaliador;
    }

    public String getNomeAvaliador() {
        return nomeAvaliador;
    }

    public void setNomeAvaliador(String nomeAvaliador) {
        this.nomeAvaliador = nomeAvaliador;
    }

    public String getCpfAvaliado() {
        return cpfAvaliado;
    }

    public void setCpfAvaliado(String cpfAvaliado) {
        this.cpfAvaliado = cpfAvaliado;
    }

    public String getNomeAvaliado() {
        return nomeAvaliado;
    }

    public void setNomeAvaliado(String nomeAvaliado) {
        this.nomeAvaliado = nomeAvaliado;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }
}
