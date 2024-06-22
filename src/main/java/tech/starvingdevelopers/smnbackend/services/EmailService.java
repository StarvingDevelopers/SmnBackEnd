package tech.starvingdevelopers.smnbackend.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tech.starvingdevelopers.smnbackend.models.email.EmailBase;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(EmailBase email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("SMN");
        message.setTo(email.getTo());
        message.setSubject(email.getEmailType().getTitle());
        message.setText(email.getFormattedEmailContent());
        mailSender.send(message);
    }
}
