package br.com.exercito.arquivo.api.impl;

import br.com.exercito.arquivo.api.interfaces.PessoaController;
import br.com.exercito.arquivo.dto.PessoaDTO;
import br.com.exercito.arquivo.exceptions.ResourceNotFoundException;
import br.com.exercito.arquivo.services.impl.PessoaServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PessoaControllerImpl implements PessoaController {

    private static final Logger logger = LoggerFactory.getLogger(PessoaControllerImpl.class);
    private final PessoaServiceImpl pessoaServiceImpl;

    public PessoaControllerImpl(PessoaServiceImpl pessoaServiceImpl) {
        this.pessoaServiceImpl = pessoaServiceImpl;
    }

    @PreAuthorize("hasRole('client_admin')")
    @GetMapping("/persons")
    public ResponseEntity<Page<PessoaDTO>> findAll(Pageable pageable) {
        Page<PessoaDTO> page = pessoaServiceImpl.findAll(pageable);

        logger.info("Listando todas as pessoas");

        return ResponseEntity.ok().body(page);
    }

    @PreAuthorize("hasRole('client_admin')")
    @GetMapping("/persons/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable("id") Long id) {
        Optional<PessoaDTO> personDTO = pessoaServiceImpl.findById(id);
        if (personDTO.isPresent()) {
            logger.info("Encontrada pessoa com ID: {}", id);
            return ResponseEntity.ok(personDTO.get());
        } else {
            logger.error("Pessoa com ID {} não encontrada", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado");
        }
    }

    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    @GetMapping("/persons/search")
    public ResponseEntity<List<PessoaDTO>> findByName(@RequestParam("nomeCadastrado") String nomeCadastrado) {
        try {
            List<PessoaDTO> personDTOs = pessoaServiceImpl.findByName(nomeCadastrado);

            logger.info("Listando as pessoas por nome");

            return ResponseEntity.ok().body(personDTOs);
        } catch (ResourceNotFoundException ex) {
            logger.error("Nenhuma pessoa encontrada com o nome: {}", nomeCadastrado);
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('client_admin')")
    @PostMapping("/persons")
    public ResponseEntity<PessoaDTO> insert(@Valid @RequestBody PessoaDTO dto) {
        dto = pessoaServiceImpl.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.id()).toUri();

        logger.info("Inserindo uma nova pessoa");
        return ResponseEntity.created(uri).body(dto);
    }

    @PreAuthorize("hasRole('client_admin')")
    @PutMapping("/persons/{id}")
    public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @Valid @RequestBody PessoaDTO dto) {
        dto = pessoaServiceImpl.update(id, dto);

        logger.info("Atualizando uma pessoa com ID: {}", id);
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasRole('client_admin')")
    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pessoaServiceImpl.delete(id);

        logger.info("Deletando uma pessoa com ID: {}", id);

        return ResponseEntity.noContent().build();
    }
}
