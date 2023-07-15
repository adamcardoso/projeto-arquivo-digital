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

    public void solicitarProcesso(String processo) {
        try {
            emailServiceImpl.sendEmail("arquivodigitalsvp3@outlook.com", "Solicitação de Processo", "Por favor, separe o processo " + processo + ".");
        } catch (MessagingException e) {
            // Tratar a exceção aqui (pode ser lançada novamente, logada, etc.)
            e.printStackTrace();
        }
    }
}
