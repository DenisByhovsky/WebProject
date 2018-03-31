package com.epam.totalizator.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailThread extends Thread {

    private static final Logger LOGGER = LogManager.getLogger(MailThread.class.getName());

    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;
    private String name;
    private Properties properties;

    public MailThread(String sendToEmail, String mailSubject, String name, String mailText, Properties properties) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.name = name;
        this.properties = properties;
    }

    private void init() {

        Session mailSession = (new MailSender(properties)).configSession();
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        try {
            message.setSubject(mailSubject);
            message.setContent((" NAME: " + name + " MESSAGE: " + mailText), "text/html");

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("denisbyh7@gmail.com"));

        } catch (AddressException e) {
            LOGGER.log(Level.ERROR, "Incorrect address");
            System.err.print("Некорректный адрес:" + sendToEmail + " " + e);
        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR, "Error in make message");
        }
    }

    public void run() {
        init();
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR, "Error send command");
        }
    }
}