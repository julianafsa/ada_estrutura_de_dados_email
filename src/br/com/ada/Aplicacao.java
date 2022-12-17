package br.com.ada;

import br.com.ada.enumerations.CountryDomain;
import br.com.ada.map.MailMap;
import br.com.ada.model.Email;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Aplicacao {

    public static void main(String[] args) {

        // TESTING ITEM b)
        MailMap inbox = Aplicacao.createInbox();
        System.out.println("====== Item b) Guardar um novo e-mail recebido. ======");
        inbox.print();

        // TESTING ITEM a)
        System.out.println("\n====== Item a) O total de endereços de e-mail diferentes é: " + inbox.getTotalOfEmailAddress() + " ======");
        inbox.printKeys();

        // TESTING ITEM c)
        System.out.println("\n====== Item c) ======");
        final String sender1 = "jean@email.com.pt";
        final String sender2 = "promo@email.com.br";
        final String sender3 = "alan@email.com.br";
        final String sender5 = "naoexiste@email.com.br";
        System.out.println("Quantidade de e-mails recebidos do " + sender1 + ": " + inbox.getTotalEmailReceveidFromSender(sender1));
        System.out.println("Quantidade de e-mails recebidos do " + sender2 + ": " + inbox.getTotalEmailReceveidFromSender(sender2));
        System.out.println("Quantidade de e-mails recebidos do " + sender3 + ": " + inbox.getTotalEmailReceveidFromSender(sender3));
        System.out.println("Quantidade de e-mails recebidos do " + null + ": " + inbox.getTotalEmailReceveidFromSender(null));
        System.out.println("Quantidade de e-mails recebidos do " + sender5 + ": " + inbox.getTotalEmailReceveidFromSender(sender5));

        // TESTING ITEM d)
        System.out.println("\n====== Item d) ======");
        final List<String> words = Arrays.asList("desconto", "ano");
        List<String> emailAddresses = inbox.getEmailAddressesListWithWordsOnSubject(words);
        System.out.print("Endereços de e-mail com e-mails contendo pelo menos uma das seguintes palavras: ");
        words.forEach(w-> System.out.print(w + " "));
        System.out.println();
        emailAddresses.forEach(System.out::println);

        // TESTING ITEM e)
        System.out.println("\n====== Item e) ======");
        Set<String> emailAddressesAsSet = inbox.getEmailAddressesSetWithWordsOnSubject(words);
        System.out.print("Endereços de e-mail com e-mails contendo pelo menos uma das seguintes palavras: ");
        words.forEach(w-> System.out.print(w + " "));
        System.out.println();
        emailAddressesAsSet.forEach(System.out::println);

        // TESTING ITEM f)
        System.out.println("\n====== Item f) ======");
        System.out.println("Removendo e-mails antes de 15/12/2022...");
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        inbox.removeEmailsReceivedBeforeDate(LocalDateTime.parse("2022-12-15 00:00", formatter));
        inbox.print();

        // TESTING ITEM g)
        System.out.println("\n====== Item g) ======");
        System.out.println("Obtendo endereços de e-mail que enviaram e-mail hoje...");
        inbox = Aplicacao.createInbox(); // Reset inbox
        List<String> emailAddressesSentEmailsToday = inbox.getEmailAddressesSentEmailsToday();
        emailAddressesSentEmailsToday.forEach(System.out::println);

        // TESTING ITEM h)
        System.out.println("\n====== Item h) ======");
        final String senderH = "promo@email.com.br";
        final List<String> wordsH = List.of("desconto");
        System.out.print("Removendo e-mails do " + senderH + " com as seguintes palavras: ");
        wordsH.forEach(System.out::print);
        System.out.println("");
        inbox = Aplicacao.createInbox(); // Reset inbox
        inbox.removeEmailsFromSenderWithWordsOnSubject(senderH, wordsH);
        inbox.print();

        // TESTING ITEM i)
        System.out.println("\n====== Item i) ======");
        final String senderI = "promo@email.com.br";
        final LocalDateTime dateI = LocalDateTime.parse("2022-12-14 00:00", formatter);
        System.out.print("Removendo e-mails do " + senderI + " anteriores a data " + dateI.toString() + "\n");
        inbox = Aplicacao.createInbox(); // Reset inbox
        inbox.removeEmailsReceivedFromSenderBeforeDate("promo@email.com.br", dateI);
        inbox.print();

        // TESTING ITEM j)
        System.out.println("\n====== Item j) ======");
        final CountryDomain countryDomain = CountryDomain.PORTUGAL;
        System.out.println("Listando e-mails oriundos de " + countryDomain.getName() + ":");
        inbox = Aplicacao.createInbox(); // Reset inbox
        List<String> emails = inbox.getEmailsFromCountry(countryDomain);
        emails.forEach(System.out::println);
    }

    public static MailMap createInbox() {
        final Email email1 = new Email(
                "alan@email.com.br",
                Arrays.asList("juliana@email.com.br", "jeanne@email.com.br"),
                "Aula de reposição",
                "A aula será resposta na próxima quinta-feira");
        email1.setSendDate(LocalDateTime.now());
        email1.setReceiptDate(LocalDateTime.now());

        final Email email2 = new Email(
                "jean@email.com.pt",
                List.of("juliana@email.com.br"),
                "Período de férias",
                "Estarei de férias durante todo o mês de janeiro de 2023. Obrigada.");
        final String sendDate2 = "2022-12-15 12:30";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        email2.setSendDate(LocalDateTime.parse(sendDate2, formatter));
        email2.setReceiptDate(LocalDateTime.parse(sendDate2, formatter).plusMinutes(2));

        final Email email3 = new Email(
                "promo@email.com.br",
                List.of("juliana@email.com.br"),
                "Super desconto de final de ano",
                "Clique aqui e veja todas as nossas promoções de final de ano.");
        email3.setSendDate(LocalDateTime.parse("2022-12-14 00:35", formatter));
        email3.setReceiptDate(LocalDateTime.parse("2022-12-14 00:36", formatter));

        final Email email4 = new Email(
                "promo@email.com.br",
                List.of("juliana@email.com.br"),
                "Desconto de Natal e de Ano Novo também",
                "Promos de final de ano.");
        email4.setSendDate(LocalDateTime.parse("2022-12-01 10:10", formatter));
        email4.setReceiptDate(LocalDateTime.parse("2022-12-01 10:11", formatter));

        final Email email5 = new Email(
                "promo@email.com.br",
                List.of("juliana@email.com.br"),
                "Liquidação de Natal",
                "Promoções de Natal.");
        email5.setSendDate(LocalDateTime.parse("2022-12-02 14:20", formatter));
        email5.setReceiptDate(LocalDateTime.parse("2022-12-02 14:25", formatter));

        // TESTING ITEM b)
        MailMap inbox = new MailMap();
        inbox.addEmailReceived(email1);
        inbox.addEmailReceived(email2);
        inbox.addEmailReceived(email3);
        inbox.addEmailReceived(email4);
        inbox.addEmailReceived(email5);

        return inbox;
    }
}
