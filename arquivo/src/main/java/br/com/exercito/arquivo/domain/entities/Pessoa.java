package br.com.exercito.arquivo.domain.entities;

import br.com.exercito.arquivo.domain.entities.enums.CategoriaDaPessoa;
import br.com.exercito.arquivo.domain.entities.enums.SituacaoDoDocumento;
import br.com.exercito.arquivo.domain.entities.enums.PostoEGraduacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_pessoa")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Código de identificação da pessoa")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nome identificação da pessoa")
    private String nomeCadastrado;
    @Schema(description = "Identidade da pessoa")
    private String numeroDaIdentidade;

    @Schema(description = "Posto e graduação, caso não seja militar, informar 'NAO_POSSUI'")
    @Enumerated(EnumType.STRING)
    private PostoEGraduacao postoGraduacao;
    @Schema(description = "Descrição")
    private String descricao;
    @Schema(description = "Número da caixa que o processo se encontra, se for o caso")
    private String numeroDaCaixa;
    @Schema(description = "Catgorias: 'VETERANO', 'PENSIONISTA MILITAR', 'PENSIONISTA CIVIL', 'INATIVO CIVIL'")
    @Enumerated(EnumType.STRING)
    private CategoriaDaPessoa categoriaDaPessoa;
    @Schema(description = "Situação: 'EMPRESTADO', 'ARQUIVADO', 'NAO_ENCONTRADO'")
    @Enumerated(EnumType.STRING)
    private SituacaoDoDocumento situacaoDoDocumento;

    @Schema(description = "Data/hora/minuto e segundo em que o processo foi devolvido")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataDeEntrada;
    @Schema(description = "Data/hora/minuto e segundo em que o processo foi emprestado")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataDeSaida;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa pessoa)) return false;

        return Objects.equals(id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

