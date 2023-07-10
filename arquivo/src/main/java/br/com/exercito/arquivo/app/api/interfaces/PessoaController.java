package br.com.exercito.arquivo.app.api.interfaces;

import br.com.exercito.arquivo.app.dto.PessoaDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "API Processos", description = "API para acesso aos endpoints de processo.")
@OpenAPIDefinition(
        info =@Info(
                title = "Pessoa API",
                version = "${api.version}",
                contact = @Contact(
                        name = "Adam & Hiago", email = "adamjuniorcardoso@gmail.com", url = "https://www.linkedin.com/in/adam-cardoso/"
                ),
                license = @License(
                        name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"
                ),
                termsOfService = "${tos.uri}",
                description = "${api.description}"
        ),
        servers = @Server(
                url = "${api.server.url}",
                description = "Production"
        )
)
public interface PessoaController {
    @Operation(description = "Buscar todas as pessoas")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/persons")
    ResponseEntity<Page<PessoaDTO>> findAll(Pageable pageable);

    @Operation(description = "Buscar pessoa por ID")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    @SecurityRequirement(name = "Bearer Authentication")
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
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/persons")
    ResponseEntity<PessoaDTO> insert(@Parameter(description = "Dados da pessoa") PessoaDTO dto);

    @Operation(description = "Atualizar uma pessoa por ID")
    @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/persons/{id}")
    ResponseEntity<PessoaDTO> update(@Parameter(description = "ID da pessoa") @PathVariable("id") Long id, @Parameter(description = "Dados atualizados da pessoa") PessoaDTO dto);

    @Operation(description = "Excluir uma pessoa por ID")
    @ApiResponse(responseCode = "204", description = "Pessoa excluída com sucesso")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/persons/{id}")
    ResponseEntity<Void> delete(@Parameter(description = "ID da pessoa") @PathVariable("id") Long id);
}
