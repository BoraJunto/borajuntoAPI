package com.borajunto.projetobora.entidade;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "BJT_AVALIACAO")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carona_id", nullable = false)
    private Carona carona;

    @ManyToOne
    @JoinColumn(name = "cpf_avaliador", nullable = false)
    private Usuario avaliador;

    @ManyToOne
    @JoinColumn(name = "cpf_avaliado", nullable = false)
    private Usuario avaliado;

    @Column(nullable = false)
    private Integer nota;

    @Column(nullable = true, length = 500)
    private String comentario;

    @Column(nullable = false)
    private LocalDateTime dataAvaliacao;

    public Avaliacao() {
        this.dataAvaliacao = LocalDateTime.now();
    }

    public Avaliacao(Carona carona, Usuario avaliador, Usuario avaliado, Integer nota, String comentario) {
        this.carona = carona;
        this.avaliador = avaliador;
        this.avaliado = avaliado;
        this.nota = nota;
        this.comentario = comentario;
        this.dataAvaliacao = LocalDateTime.now();
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

    public Usuario getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(Usuario avaliador) {
        this.avaliador = avaliador;
    }

    public Usuario getAvaliado() {
        return avaliado;
    }

    public void setAvaliado(Usuario avaliado) {
        this.avaliado = avaliado;
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
