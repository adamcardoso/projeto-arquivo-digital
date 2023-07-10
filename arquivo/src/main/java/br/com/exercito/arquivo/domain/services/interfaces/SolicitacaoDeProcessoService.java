package br.com.exercito.arquivo.domain.services.interfaces;

import javax.mail.MessagingException;

public interface SolicitacaoDeProcessoService {
    void solicitarProcesso(String processo) throws MessagingException;
}
