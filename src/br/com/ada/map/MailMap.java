package br.com.ada.map;

import br.com.ada.enumerations.CountryDomain;
import br.com.ada.model.Email;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class MailMap {

    private final Map<String, List<Email>> emailMap = new HashMap<>();

    public MailMap() {}

    /**
     * a) Determinar o total de endereços a partir dos quais se recebeu e-mail.
     * @return Total de endereços diferentes a partir dos quais se recebeu e-mail.
     */
    public Integer getTotalOfEmailAddress() {
        return emailMap.size();
    }

    public void printKeys() {
        for (final String sender: emailMap.keySet()) {
            System.out.println(sender);
        }
    }

    /**
     * b) Guardar um novo e-mail recebido.
     * @param email E-mail recebido.
     */
    public void addEmailReceived(final Email email) {
        if (email != null) {
            final String sender = email.getSender();
            if (emailMap.containsKey(sender)) {
                List<Email> emailsReceived = emailMap.get(sender);
                emailsReceived.add(email);
            } else {
                List<Email> emailsReceived = new ArrayList<>();
                emailsReceived.add(email);
                emailMap.put(sender, emailsReceived);
            }
        } else {
            System.out.println("Parâmetro e-mail não pode ser nulo.");
        }
    }

    /**
     * c) Determinar quantos e-mails têm por origem um dado endereço.
     * @param sender Endereço de e-mail a ser buscado a quantidade de e-mails recebidos.
     * @return Quantidade de e-mails recebidos de um dado remetente.
     */
    public Integer getTotalEmailReceveidFromSender(final String sender) {
        Integer total = 0;
        if (sender == null || sender.isBlank()) {
            System.out.println("Sender parameter cannot be null.");
        }
        if (emailMap.containsKey(sender)) {
            total = emailMap.get(sender).size();
        }
        return total;
    }

    /**
     * d) Criar uma lista com todos os endereços que enviaram e-mails contendo no seu assunto
     * pelo menos uma palavra dentre uma lista de palavras dada como parâmetro.
     * Observação: No dia 16/12/2022, o Professor Alan confirmou na aula que os e-mails precisam
     * conter pelo menos uma palavra da lista e não todas.
     * @param words Lista de palavras.
     * @return Lista com todos os endereços que enviaram e-mails contendo no seu assunto pelo menos
     * uma palavra dentre uma lista de palavras dada como parâmetro.
     */
    public List<String> getEmailAddressesListWithWordsOnSubject(final List<String> words) {
        final List<String> emailAddresses = new ArrayList<>();
        return (List<String>) getEmailAddressesSetWithWordsOnSubject(words, emailAddresses);
    }

    // 5. O mesmo que a questão anterior, mas criando um conjunto contendo os e-mails;

    /**
     * e) O mesmo que a questão anterior, mas criando um conjunto contendo os e-mails.
     * @param words Lista de palavras.
     * @return Conjunto com todos os endereços que enviaram e-mails contendo no seu assunto pelo menos
     * uma palavra dentre uma lista de palavras dada como parâmetro.
     */
    public Set<String> getEmailAddressesSetWithWordsOnSubject(final List<String> words) {
        final Set<String> emailAddresses = new HashSet<>();
        return (Set<String>) getEmailAddressesSetWithWordsOnSubject(words, emailAddresses);
    }

    private Collection<String> getEmailAddressesSetWithWordsOnSubject(final List<String> words,
                                                                      final Collection<String> emailAddresses) {
        for (Map.Entry<String, List<Email>> entry: emailMap.entrySet()) {
            final String sender = entry.getKey();
            final List<Email> emailReceived = entry.getValue();
            for (Email email : emailReceived) {
                if (email.hasAtLeastAWordOnSubjectIgnoringCase(words)) {
                    emailAddresses.add(sender);
                    break; // Go to next sender
                }
            }
        }
        return emailAddresses;
    }

    /**
     * f) Eliminar todos os e-mails recebidos antes de uma data que é dada como parâmetro;
     * @param date Data para comparação.
     */
    public void removeEmailsReceivedBeforeDate(final LocalDateTime date) {
        final List<String> sendersToRemove = new ArrayList<>();
        for (Map.Entry<String, List<Email>> entry: emailMap.entrySet()) {
            final String sender = entry.getKey();
            final List<Email> emailsReceived = entry.getValue();
            emailsReceived.removeIf(email -> email.getReceiptDate().isBefore(date));
            if (emailsReceived.isEmpty()) {
                sendersToRemove.add(sender); // Senders with no e-mails
            }
        }
        sendersToRemove.forEach(emailMap::remove); // Remove senders with no e-mails
    }

    /**
     * g) Criar uma lista dos endereços que hoje enviaram e-mails.
     * @return Lista de endereços de e-mails.
     */
    public List<String> getEmailAddressesSentEmailsToday() {
        final List<String> emails = new ArrayList<>();
        final LocalDate date = LocalDateTime.now().toLocalDate();
        for (Map.Entry<String, List<Email>> entry: emailMap.entrySet()) {
            final String sender = entry.getKey();
            final List<Email> emailsReceived = entry.getValue();
            for (Email email: emailsReceived) {
                if (email.getReceiptDate().toLocalDate().equals(date)) {
                    emails.add(sender);
                    break; // Get to next sender
                }
            }
        }
        return emails;
    }

    /**
     * h) Dada uma lista de palavras, eliminar todos os e-mails de um dado endereço que no seu assunto
     * contenham uma qualquer destas (anti-spam);
     * @param sender Remetente do e-mail.
     * @param words Lista de palavras.
     */
    public void removeEmailsFromSenderWithWordsOnSubject(final String sender, final List<String> words) {
        if (sender == null || sender.isBlank()) {
            System.out.println("Sender parameter cannot be null.");
            return;
        }
        if (emailMap.containsKey(sender)) {
            final List<Email> emailsReceived = emailMap.get(sender);
            emailsReceived.removeIf(email -> email.hasAtLeastAWordOnSubjectIgnoringCase(words));
            if (emailsReceived.isEmpty()) {
                emailMap.remove(sender); // Remove sender with no e-mails
            }
        } else {
            System.out.println("Sender not found.");
        }
    }

    /**
     * i) Eliminar todos os e-mails de um dado endereço anteriores a uma data dada.
     * @param sender
     * @param date
     */
    public void removeEmailsReceivedFromSenderBeforeDate(final String sender, final LocalDateTime date) {
        if (sender == null || sender.isBlank() || date == null) {
            System.out.println("Sender and date parameters cannot be null or blank.");
            return;
        }
       if (emailMap.containsKey(sender)) {
           final List<Email> emailsReceived = emailMap.get(sender);
           emailsReceived.removeIf(email -> email.getReceiptDate().isBefore(date));
           if (emailsReceived.isEmpty()) {
               emailMap.remove(sender); // Senders with no e-mails
           }
       }
    }

    /**
     * j) Criar uma listagem com todos os endereços de e-mail oriundos um país dado como parâmetro.
     * @param countryDomain
     * @return
     */
    public List<String> getEmailsFromCountry(final CountryDomain countryDomain) {
        final List<String> emailsFromCountry = new ArrayList<>();
        if (countryDomain == null) {
            System.out.println("countryDomain parameter cannot be null.");
            return emailsFromCountry;
        }
        for (String sender: emailMap.keySet()) {
            if (sender.endsWith(countryDomain.getDomain())) {
                emailsFromCountry.add(sender);
            }
        }
        return emailsFromCountry;
    }

    @Override
    public String toString() {
        return "MailMap{" +
                "emailMap=" + emailMap +
                '}';
    }

    public void print() {
        String result = "MailMap{";
        for (Map.Entry<String, List<Email>> entry: emailMap.entrySet()) {
            System.out.println(entry.getKey() + ": ");
            for (int i = 0; i < entry.getValue().size(); i++) {
                final Email email = entry.getValue().get(i);
                System.out.println("Email " + i + ": " + email.toString());
            }
            System.out.println("");
        }
    }
}
