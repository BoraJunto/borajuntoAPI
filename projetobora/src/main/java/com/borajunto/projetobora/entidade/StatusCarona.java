package com.borajunto.projetobora.entidade;

public enum StatusCarona {
    ATIVA("Ativa"),
    CANCELADA("Cancelada"),
    FINALIZADA("Finalizada"),
    COMPLETA("Completa");

    private final String descricao;

    StatusCarona(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
