package br.com.ada.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Email {

    private String sender;
    private List<String> recipients = new ArrayList<>();
    private List<String> cc = new ArrayList<>();
    private List<String> bcc = new ArrayList<>();
    private LocalDateTime sendDate;
    private LocalDateTime receiptDate;
    private String subject;
    private String body;

    public Email(String sender, List<String> recipients, String subject, String body) {
        this.sender = sender;
        this.recipients = recipients;
        this.subject = subject;
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getRecipients() {
        return List.copyOf(recipients);
    }

    public void addRecipient(String recipient) {
        this.recipients.add(recipient);
    }

    public List<String> getCc() {
        return List.copyOf(cc);
    }

    public void addCc(String cc) {
        this.cc.add(cc);
    }

    public List<String> getBcc() {
        return List.copyOf(bcc);
    }

    public void addBcc(String bcc) {
        this.bcc.add(bcc);
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }

    public LocalDateTime getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(LocalDateTime receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
