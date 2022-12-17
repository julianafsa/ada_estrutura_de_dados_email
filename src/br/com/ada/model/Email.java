package br.com.ada.model;

import br.com.ada.enumerations.CountryDomain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Email {

    private String sender;
    private List<String> recipients = new ArrayList<>();
    private final List<String> cc = new ArrayList<>();
    private final List<String> bcc = new ArrayList<>();
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

    public Boolean hasAtLeastAWordOnSubject(final List<String> words) {
        Boolean result = Boolean.FALSE;
        if (this.subject != null && !this.subject.isBlank()) {
            for (String word: words) {
                if (this.subject.contains(word)) {
                    result = Boolean.TRUE;
                    break;
                }
            }
        }
        return result;
    }

    public Boolean hasAtLeastAWordOnSubjectIgnoringCase(final List<String> words) {
        Boolean result = Boolean.FALSE;
        if (this.subject != null && !this.subject.isBlank()) {
            for (String word: words) {
                final String lowerCaseSubject = this.subject.toLowerCase();
                if (lowerCaseSubject.contains(word.toLowerCase())) {
                    result = Boolean.TRUE;
                    break;
                }
            }
        }
        return result;
    }

    public Boolean isFromCountry(final CountryDomain countryDomain) {
        if (this.sender.endsWith(countryDomain.getDomain())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public String toString() {
        return "Email{" +
                "sender='" + sender + '\'' +
                ", recipients=" + recipients +
                //", cc=" + cc +
                //", bcc=" + bcc +
                ", sendDate=" + sendDate +
                ", receiptDate=" + receiptDate +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
