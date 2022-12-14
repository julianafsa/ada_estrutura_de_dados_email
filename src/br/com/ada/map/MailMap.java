package br.com.ada.map;

import br.com.ada.model.Email;

import java.time.LocalDateTime;
import java.util.*;

public class MailMap {

    private Map<Email, List<Email>> emailMap = new HashMap<>();

    public MailMap() {}

    /**
     * Determinar o total de endereços a partir dos quais se recebeu e-mail
     * @param emailSender The sender of email.
     * @return total of emails received.
     */
    public Integer getTotalNumberOfEmailsReceived(final String emailSender) {
        Integer total = 0;
        if (emailMap.containsKey(emailSender)) {
            total = emailMap.get(emailSender).size();
        }
        return total;
    }

    // 2. Guardar um novo mail recebido
    public void addEmailReceived(final Email email) {
        if (email != null) {
            final String sender = email.getSender();
            if (emailMap.containsKey(sender)) {
                List<Email> emailsReceived = emailMap.get(sender);
                emailsReceived.add(email);
            }
        } else {
            // TODO Levantar exception
        }
    }

    // 3. Determinar quantos e-mails têm por origem um dado endereço
    public Integer getTotalEmailReceveidFromSender(final String emailSender) {
        if (emailSender == null || emailSender.isBlank()) {
            // TODO Levantar exception
        }
        Integer totalEmailReceivedFromSender = 0;
        for (Map.Entry<Email, List<Email>> entry: emailMap.entrySet()) {
            List<Email> emailReceived = entry.getValue();
            for (Email email : emailReceived) {
                if (email.getSender().equals(emailSender)) {
                    totalEmailReceivedFromSender++;
                }
            }
        }
        return totalEmailReceivedFromSender;
    }

    // 4. Criar uma lista com todos os endereços que enviaram e-mails contendo no seu assunto
    // uma lista de palavras dada como parâmetro;
    private List<String> getEmailsReceivedWithSubject(final List<String> subjects) {
        List<String> emails = new ArrayList<>();
        for (Map.Entry<Email, List<Email>> entry: emailMap.entrySet()) {
            List<Email> emailReceived = entry.getValue();
            for (Email email : emailReceived) {
                final String subject = email.getSubject();
                Boolean contains = Boolean.TRUE;
                for (String word : subjects) {
                    if (!subject.contains(word)) {
                        contains = Boolean.FALSE;
                        break; // Go to next email
                    }
                }
                if (contains) {
                    emails.add(email.getSender());
                }
            }
        }
        return emails; // TODO Simplificar
    }

    // 5. O mesmo que a questão anterior, mas criando um conjunto contendo os mails;
    private Set<Email> getEmailsSetReceivedWithSubject(final List<String> subjects) {
        Set<Email> emails = new HashSet<>();
        for (Map.Entry<Email, List<Email>> entry: emailMap.entrySet()) {
            List<Email> emailReceived = entry.getValue();
            for (Email email : emailReceived) {
                final String subject = email.getSubject();
                Boolean contains = Boolean.TRUE;
                for (String word : subjects) {
                    if (!subject.contains(word)) {
                        contains = Boolean.FALSE;
                        break; // Go to next email
                    }
                }
                if (contains) {
                    emails.add(email);
                }
            }
        }
        return emails; // TODO Simplificar
    }

    // 6. Eliminar todos os e-mails recebidos antes de uma data que é dada como parametro;
    public void removeEmailsReceivedBeforeDate(final LocalDateTime date) {
        for (Map.Entry<Email, List<Email>> entry: emailMap.entrySet()) {
            List<Email> emailsReceived = entry.getValue();
            for (Email email: emailsReceived) {
                if (email.getReceiptDate().isBefore(date)) {
                    // TODO Testar - Posso remover enquanto percorro a lista? Ou salvo referência dos que tenho que remover?
                    emailsReceived.remove(email);
                }
            }
        }
    }

    // 7. Criar uma lista dos endereços que hoje enviaram e-mails
    // 8. Dada uma lista de palavras, eliminar todos os e-mails de um dado endereço que no seu
    // assunto contenham qualquer uma destas (anti-spam);
    // 9. Eliminar todos os e-mails de um dado endereço anteriores a uma data dada;
    // 10. Criar uma listagem com todos os endereços de e-mail oriundos de Portugal
}
