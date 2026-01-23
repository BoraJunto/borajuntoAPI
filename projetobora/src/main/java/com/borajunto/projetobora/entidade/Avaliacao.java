package com.borajunto.projetobora.entidade;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "BJT_AVALIACAO")
@Getter
@Setter
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
}
