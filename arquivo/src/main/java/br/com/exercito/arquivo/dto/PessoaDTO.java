package br.com.exercito.arquivo.dto;

import br.com.exercito.arquivo.entities.Pessoa;
import br.com.exercito.arquivo.entities.enums.CategoriaDaPessoa;
import br.com.exercito.arquivo.entities.enums.PostoEGraduacao;
import br.com.exercito.arquivo.entities.enums.SituacaoDoDocumento;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record PessoaDTO(
        Long id,
        String nomeCadastrado,
        String numeroDaIdentidade,
        PostoEGraduacao postoGraduacao,
        String descricao,
        String numeroDaCaixa,
        CategoriaDaPessoa categoriaDaPessoa,
        SituacaoDoDocumento situacaoDoDocumento,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataDeEntrada,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataDeSaida
) {
    public PessoaDTO(Pessoa pessoa) {
        this(
                pessoa.getId(),
                pessoa.getNomeCadastrado(),
                pessoa.getNumeroDaIdentidade(),
                pessoa.getPostoGraduacao(),
                pessoa.getDescricao(),
                pessoa.getNumeroDaCaixa(),
                pessoa.getCategoriaDaPessoa(),
                pessoa.getSituacaoDoDocumento(),
                pessoa.getDataDeEntrada(),
                pessoa.getDataDeSaida()
        );
    }
}
