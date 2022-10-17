package com.john.rest_spring_boot_testing.services.email;

import com.john.rest_spring_boot_testing.models.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailWithAttachment(Email email) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom(email.getFromemail());
        mimeMessageHelper.setTo("gualterosjohn40@gmail.com");
        mimeMessageHelper.setText(email.getBody());
        mimeMessageHelper.setSubject(email.getSubject());

        FileSystemResource fileSystem = new FileSystemResource(new File(email.getAttachment()));

        mimeMessageHelper.addAttachment(fileSystem.getFilename(),fileSystem);

        mailSender.send(mimeMessage);
    }
}
