package br.com.exercito.arquivo;

import br.com.exercito.arquivo.entities.Pessoa;
import br.com.exercito.arquivo.repositories.PessoaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = PessoaRepository.class)
@EntityScan(basePackageClasses = Pessoa.class)
public class ArquivoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArquivoApplication.class, args);
    }

}
