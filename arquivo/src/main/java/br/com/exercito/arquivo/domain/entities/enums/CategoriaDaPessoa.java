package br.com.exercito.arquivo.domain.entities.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public enum CategoriaDaPessoa {
    VETERANO("Veterano"),
    PENSIONISTA_MILITAR("Pensionista Militar"),
    PENSIONISTA_CIVIL("Pensionista Civil"),
    INATIVO_CIVIL("Inativo Civil");

    private final String descricao;

    CategoriaDaPessoa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}