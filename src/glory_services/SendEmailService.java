/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glory_services;

import java.net.URL;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author TeamStark
 */
public class SendEmailService {

    protected String genaratedCode;

    public void sendVerificationCode(String Code, String PlayerEmail) {

        final String username = "TeamStarkBatch7@gmail.com";
        final String password = "TeamStarkBatch7Share";
        genaratedCode = Code;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("TeamStarkBatch7@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(PlayerEmail));
            message.setSubject("Glory Game Verification Code");
            message.setContent(String.format("<h1>This is your verification Code</h1>"
                    + "<h2>%s</h2>", Code), "text/html");
            Transport.send(message);          
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
