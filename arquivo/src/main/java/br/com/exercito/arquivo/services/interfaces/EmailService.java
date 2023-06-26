package br.com.exercito.arquivo.services.interfaces;

import javax.mail.MessagingException;

public interface EmailService {
    void sendEmail(String to, String subject, String text) throws MessagingException;
}
