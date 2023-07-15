package br.com.exercito.arquivo.app.api.impl;

import br.com.exercito.arquivo.app.api.interfaces.PessoaController;
import br.com.exercito.arquivo.app.dto.PessoaDTO;
import br.com.exercito.arquivo.domain.services.interfaces.PessoaService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = {"http://localhost:3000/", "http://localhost:8081"})
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
@RestController
@RequestMapping("/api")
public class PessoaControllerImpl implements PessoaController {

    private static final Logger logger = LoggerFactory.getLogger(PessoaControllerImpl.class);
    private final PessoaService pessoaService;

    public PessoaControllerImpl(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PreAuthorize("hasRole('client_admin')")
    @GetMapping("/persons")
    public ResponseEntity<Page<PessoaDTO>> findAll(Pageable pageable) {
        Page<PessoaDTO> page = pessoaService.findAll(pageable);

        logger.info("Listando todas as pessoas");

        return ResponseEntity.ok().body(page);
    }

    @PreAuthorize("hasRole('client_admin')")
    @GetMapping("/persons/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable("id") Long id) {
        Optional<PessoaDTO> personDTO = pessoaService.findById(id);
        if (personDTO.isPresent()) {
            logger.info("Encontrada pessoa com ID: {}", id);
            return ResponseEntity.ok(personDTO.get());
        } else {
            logger.error("Pessoa com ID {} não encontrada", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    @GetMapping("/persons/search")
    public ResponseEntity<List<PessoaDTO>> findByName(@RequestParam("nomeCadastrado") String nomeCadastrado) {
        List<PessoaDTO> personDTOs = pessoaService.findByName(nomeCadastrado);

        logger.info("Listando as pessoas por nome");

        return ResponseEntity.ok().body(personDTOs);
    }

    @PreAuthorize("hasRole('client_admin')")
    @PostMapping("/persons")
    public ResponseEntity<PessoaDTO> insert(@Valid @RequestBody PessoaDTO dto) {
        dto = pessoaService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.id()).toUri();

        logger.info("Inserindo uma nova pessoa");
        return ResponseEntity.created(uri).body(dto);
    }

    @PreAuthorize("hasRole('client_admin')")
    @PutMapping("/persons/{id}")
    public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @Valid @RequestBody PessoaDTO dto) {
        dto = pessoaService.update(id, dto);

        logger.info("Atualizando uma pessoa com ID: {}", id);
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasRole('client_admin')")
    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pessoaService.delete(id);

        logger.info("Deletando uma pessoa com ID: {}", id);

        return ResponseEntity.noContent().build();
    }
}
