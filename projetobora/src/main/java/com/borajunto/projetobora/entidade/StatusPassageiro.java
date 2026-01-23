package com.borajunto.projetobora.entidade;

public enum StatusPassageiro {
    PENDENTE("Pendente"),
    APROVADO("Aprovado"),
    REJEITADO("Rejeitado"),
    CANCELADO("Cancelado"),
    COMPLETADO("Completado");

    private final String descricao;

    StatusPassageiro(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
