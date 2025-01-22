package UserManagement.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.IOException;


@Service
public class EmailSender {

    private final JavaMailSender mailSender;

    public EmailSender(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void sendHtmlEmail(String email, String name) throws IOException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        message.setFrom(new InternetAddress("sohail271201@gmail.com", "Registration"));
        message.setRecipients(MimeMessage.RecipientType.TO, email);

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("sohail271201@gmail.com");
        helper.setTo(email);
        helper.setSubject("Welcome to IK Platforrm");
        helper.setText("Welcome " + name.toUpperCase() + "\n\nThanks for the Registration in the IK Management App");

        mailSender.send(message);

    }
}
