package br.com.exercito.arquivo.domain.services.impl;

import br.com.exercito.arquivo.app.dto.PessoaDTO;
import br.com.exercito.arquivo.domain.entities.Pessoa;
import br.com.exercito.arquivo.domain.exceptions.DatabaseException;
import br.com.exercito.arquivo.domain.exceptions.ResourceNotFoundException;
import br.com.exercito.arquivo.domain.services.interfaces.PessoaService;
import br.com.exercito.arquivo.infra.repositories.PessoaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {
    private final PessoaRepository pessoaRepository;
    private static final String ID_NOT_FOUND_MESSAGE = "Id não encontrado ";

    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional(readOnly = true)
    public Page<PessoaDTO> findAll(Pageable pageable) {
        Page<Pessoa> persons = pessoaRepository.findAll(pageable);
        return persons.map(PessoaDTO::new);
    }

    @Transactional
    public Optional<PessoaDTO> findById(Long id) {
        Optional<Pessoa> obj = pessoaRepository.findById(id);
        return obj.map(PessoaDTO::new);
    }

    @Transactional(readOnly = true)
    public List<PessoaDTO> findByName(String nomeCadastrado) {
        List<Pessoa> list = pessoaRepository.findByNomeCadastradoContainingIgnoreCase(nomeCadastrado);

        if (list.isEmpty()) {
            throw new ResourceNotFoundException("Nome não encontrado: " + nomeCadastrado);
        }

        return list.stream()
                .map(PessoaDTO::new)
                .toList();
    }

    public PessoaDTO insert(PessoaDTO dto) {
        Pessoa entity = new Pessoa();
        copyDtoToEntity(dto, entity);
        entity = pessoaRepository.save(entity);
        return new PessoaDTO(entity);
    }

    public PessoaDTO update(Long id, PessoaDTO dto) {
        try {
            Optional<Pessoa> optionalEntity = pessoaRepository.findById(id);
            if (optionalEntity.isPresent()) {
                Pessoa entity = optionalEntity.get();
                copyDtoToEntity(dto, entity);
                entity = pessoaRepository.save(entity);
                return new PessoaDTO(entity);
            } else {
                throw new ResourceNotFoundException(ID_NOT_FOUND_MESSAGE + id);
            }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(ID_NOT_FOUND_MESSAGE + id);
        }
    }


    public void delete(Long id) {
        try {
            pessoaRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(ID_NOT_FOUND_MESSAGE + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(PessoaDTO dto, Pessoa pessoa) {
        pessoa.setNomeCadastrado(dto.nomeCadastrado());
        pessoa.setNumeroDaIdentidade(dto.numeroDaIdentidade());
        pessoa.setPostoGraduacao(dto.postoGraduacao());
        pessoa.setDescricao(dto.descricao());
        pessoa.setNumeroDaCaixa(dto.numeroDaCaixa());
        pessoa.setCategoriaDaPessoa(dto.categoriaDaPessoa());
        pessoa.setSituacaoDoDocumento(dto.situacaoDoDocumento());
        pessoa.setDataDeEntrada(dto.dataDeEntrada());
        pessoa.setDataDeSaida(dto.dataDeSaida());
    }
}
