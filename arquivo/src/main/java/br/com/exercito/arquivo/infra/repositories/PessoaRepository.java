package br.com.exercito.arquivo.infra.repositories;

import br.com.exercito.arquivo.domain.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findByNomeCadastradoContainingIgnoreCase(String nomeCadastrado);
}
