package com.john.rest_spring_boot_testing.models;

import javax.persistence.*;

@Entity
@Table(name = "email")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="fromemail")
    private String fromemail;

    @Column(name="body", length = 300)
    private String body;

    @Column(name="subject")
    private String subject;

    @Column(name="attachment")
    private String attachment;

    public Email(Long id, String fromemail, String body, String subject, String attachment) {
        this.id = id;
        this.fromemail = fromemail;
        this.body = body;
        this.subject = subject;
        this.attachment = attachment;
    }

    public Email(String fromemail, String body, String subject, String attachment) {
        this.fromemail = fromemail;
        this.body = body;
        this.subject = subject;
        this.attachment = attachment;
    }

    public Email() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromemail() {
        return fromemail;
    }

    public void setFromemail(String fromemail) {
        this.fromemail = fromemail;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
