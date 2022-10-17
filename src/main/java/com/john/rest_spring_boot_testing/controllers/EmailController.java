package com.john.rest_spring_boot_testing.controllers;

import com.john.rest_spring_boot_testing.models.Email;
import com.john.rest_spring_boot_testing.services.email.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/v1/")
public class EmailController {

    @Autowired
    private EmailSenderService emailService;

    @PostMapping("/sendEmail")
    public void triggerMailWithAttachment(@RequestBody Email email) throws MessagingException {
        emailService.sendEmailWithAttachment(email);
        //"C:\\Users\\jumbo170\\Downloads\\example_svelte.png"
    }
}
