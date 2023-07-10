package br.com.exercito.arquivo.domain.services.interfaces;

import javax.mail.MessagingException;

public interface EmailService {
    void sendEmail(String to, String subject, String text) throws MessagingException;
}
