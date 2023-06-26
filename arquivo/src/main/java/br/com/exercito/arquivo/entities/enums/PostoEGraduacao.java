package br.com.exercito.arquivo.entities.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public enum PostoEGraduacao {
    SOLDADO,
    CABO,
    TERCEIRO_SARGENTO,
    SEGUNDO_SARGENTO,
    PRIMEIRO_SARGENTO,
    SUBTENENTE,
    ASPIRANTE,
    SEGUNDO_TENENTE,
    PRIMEIRO_TENENTE,
    CAPITAO,
    MAJOR,
    TENENTE_CORONEL,
    CORONEL,
    GENERAL_DE_BRIGADA,
    GENERAL_DE_DIVISAO,
    GENERAL_DE_EXERCITO,
    NAO_POSSUI
}
