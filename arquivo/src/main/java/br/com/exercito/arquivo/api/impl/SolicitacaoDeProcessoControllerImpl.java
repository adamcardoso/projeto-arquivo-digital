package br.com.exercito.arquivo.api.impl;

import br.com.exercito.arquivo.api.interfaces.SolicitacaoDeProcessoController;
import br.com.exercito.arquivo.services.impl.SolicitacaoDeProcessoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping(value = "/api")
public class SolicitacaoDeProcessoControllerImpl implements SolicitacaoDeProcessoController {

    private static final Logger logger = LoggerFactory.getLogger(SolicitacaoDeProcessoControllerImpl.class);

    private final SolicitacaoDeProcessoServiceImpl solicitacaoDeProcessoService;

    public SolicitacaoDeProcessoControllerImpl(SolicitacaoDeProcessoServiceImpl solicitacaoDeProcessoService) {
        this.solicitacaoDeProcessoService = solicitacaoDeProcessoService;
    }

    @Override
    @PreAuthorize("hasRole('client_user')")
    @PostMapping("/solicitar-processo")
    public ResponseEntity<String> solicitarProcesso(@RequestBody String processo) {
        try {
            solicitacaoDeProcessoService.solicitarProcesso(processo);
            logger.info("Solicitação de processo enviada com sucesso!");
            return ResponseEntity.ok("Solicitação de processo enviada com sucesso!");
        } catch (MessagingException e) {
            logger.error("Erro ao enviar a solicitação de processo.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao enviar a solicitação de processo.");
        }
    }
}
