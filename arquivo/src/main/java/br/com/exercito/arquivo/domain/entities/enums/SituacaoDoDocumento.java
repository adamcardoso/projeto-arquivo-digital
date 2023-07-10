package br.com.exercito.arquivo.domain.entities.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public enum SituacaoDoDocumento {
    EMPRESTADO,
    ARQUIVADO,
    NAO_ENCONTRADO
}
