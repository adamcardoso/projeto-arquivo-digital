package br.com.exercito.arquivo.app.api.impl;

import br.com.exercito.arquivo.app.api.interfaces.SolicitacaoDeProcessoController;
import br.com.exercito.arquivo.domain.services.interfaces.SolicitacaoDeProcessoService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {"http://localhost:3000/", "http://localhost:8081"})
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
@RestController
@RequestMapping(value = "/api")
public class SolicitacaoDeProcessoControllerImpl implements SolicitacaoDeProcessoController {

    private static final Logger logger = LoggerFactory.getLogger(SolicitacaoDeProcessoControllerImpl.class);

    private final SolicitacaoDeProcessoService solicitacaoDeProcessoService;

    public SolicitacaoDeProcessoControllerImpl(SolicitacaoDeProcessoService solicitacaoDeProcessoService) {
        this.solicitacaoDeProcessoService = solicitacaoDeProcessoService;
    }

    @Override
    @PreAuthorize("hasRole('client_user')")
    @PostMapping("/solicitar-processo")
    public ResponseEntity<String> solicitarProcesso(@RequestBody String processo) {
        solicitacaoDeProcessoService.solicitarProcesso(processo);

        logger.info("Solicitação de processo enviada com sucesso!");

        return ResponseEntity.ok("Solicitação de processo enviada com sucesso!");
    }
}
