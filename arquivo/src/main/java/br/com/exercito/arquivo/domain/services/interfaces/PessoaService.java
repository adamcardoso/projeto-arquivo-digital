package br.com.exercito.arquivo.domain.services.interfaces;

import br.com.exercito.arquivo.app.dto.PessoaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PessoaService {
    Page<PessoaDTO> findAll(Pageable pageable);
    Optional<PessoaDTO> findById(Long id);
    List<PessoaDTO> findByName(String name);
    PessoaDTO insert(PessoaDTO dto);
    PessoaDTO update(Long id, PessoaDTO dto);
    void delete(Long id);
}
