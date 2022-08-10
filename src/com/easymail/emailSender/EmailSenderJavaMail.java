package com.easymail.emailSender;

import com.easymail.historyManagement.MailsInADay;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSenderJavaMail implements IEmailSender{

    @Override
    public void sendEmail(Email email) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String emailAccount = "xxxxxxx@gmail.com";
        String password = "xxxxxxxx";
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAccount, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email.toRecipient));
            if (email.carbonCopy.length > 0)
                for (String carbonCopy : email.carbonCopy) {
                    message.setRecipient(Message.RecipientType.CC, new InternetAddress(carbonCopy));
                }
            if (email.blindCarbonCopy.length > 0)
                for (String blindCarbonCopy : email.blindCarbonCopy) {
                    message.setRecipient(Message.RecipientType.BCC, new InternetAddress(blindCarbonCopy));
                }
            message.setSubject(email.subject);
            message.setText(email.content);
            Transport.send(message);

            MailsInADay mailsInADay = MailsInADay.getInstance();
            mailsInADay.save(email);

            System.out.println("Email(s) sent successfully!");
        }
        catch (AddressException e) {
            System.out.println("Address error");
        }
        catch (MessagingException ex) {
            System.out.println("Error while sending email!");
        }
    }
}