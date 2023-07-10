package br.com.exercito.arquivo.domain.services.impl;

import br.com.exercito.arquivo.domain.services.interfaces.SolicitacaoDeProcessoService;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class SolicitacaoDeProcessoServiceImpl implements SolicitacaoDeProcessoService {

    private final EmailServiceImpl emailServiceImpl;

    public SolicitacaoDeProcessoServiceImpl(EmailServiceImpl emailService) {
        this.emailServiceImpl = emailService;
    }

    public void solicitarProcesso(String processo) throws MessagingException {
        emailServiceImpl.sendEmail("arquivodigitalsvp3@outlook.com", "Solicitação de Processo", "Por favor, separe o processo " + processo + ".");
    }
}
