package br.com.exercito.arquivo.api.interfaces;

import br.com.exercito.arquivo.dto.PessoaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "API Processos", description = "API para acesso aos endpoints de processo.")
public interface PessoaController {
    @Operation(description = "Buscar todas as pessoas")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @GetMapping("/persons")
    ResponseEntity<Page<PessoaDTO>> findAll(Pageable pageable);

    @Operation(description = "Buscar pessoa por ID")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    @GetMapping("/persons/{id}")
    ResponseEntity<PessoaDTO> findById(@Parameter(description = "ID da pessoa") @PathVariable("id") Long id);

    @Operation(description = "Buscar pessoas por nome")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Pessoas não encontradas")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    @GetMapping("/persons/search")
    ResponseEntity<List<PessoaDTO>> findByName(@Parameter(description = "Nome da pessoa") String name);

    @Operation(description = "Inserir uma nova pessoa")
    @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso")
    @PostMapping("/persons")
    ResponseEntity<PessoaDTO> insert(@Parameter(description = "Dados da pessoa") PessoaDTO dto);

    @Operation(description = "Atualizar uma pessoa por ID")
    @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso")
    @PutMapping("/persons/{id}")
    ResponseEntity<PessoaDTO> update(@Parameter(description = "ID da pessoa") @PathVariable("id") Long id, @Parameter(description = "Dados atualizados da pessoa") PessoaDTO dto);

    @Operation(description = "Excluir uma pessoa por ID")
    @ApiResponse(responseCode = "204", description = "Pessoa excluída com sucesso")
    @DeleteMapping("/persons/{id}")
    ResponseEntity<Void> delete(@Parameter(description = "ID da pessoa") @PathVariable("id") Long id);
}
