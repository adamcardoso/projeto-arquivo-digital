package br.com.exercito.arquivo.services.interfaces;

import javax.mail.MessagingException;

public interface SolicitacaoDeProcessoService {
    void solicitarProcesso(String processo) throws MessagingException;
}
