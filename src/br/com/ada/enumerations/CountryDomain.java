package br.com.ada.enumerations;
public enum CountryDomain {

    AFRICA_DO_SUL("África do Sul", "za"),
    ALBANIA("Albânia", ".al"),
    ANGOLA("Angola", ".al"),
    ARGENTINA("Argentina", ".ar"),
    AUSTRALIA("Australia", ".au"),
    AUSTRIA("Áustria", ".at"),
    BELGICA("Bélgica", ".be"),
    BRASIL("Brasil", ".br"),
    CANADA("Canadá", ".ca"),
    CHILE("Chile", ".cl"),
    EQUADOR("Equador", ".ec"),
    ESPANHA("Espanha", ".es"),
    FRANCA("França", ".fr"),
    HOLANDA("Holanda", ".nl"),
    HUNGRIA("Hungria", ".hu"),
    IRLANDA("Irlanda", ".ie"),
    ITALIA("Itália", ".it"),
    JAPAO("Japão", ".jp"),
    NORUEGA("Noruega", ".no"),
    QUENIA("Quênia", ".qe"),
    PERU("Peru", ".pe"),
    PORTUGAL("Portugal", ".pt"),
    QATAR("Qatar", ".qa"),
    SUECIA("Suécia", ".se"),
    URUGUAI("Uruguai", ".uy");

    private String name;
    private String domain;

    CountryDomain(String name, String domain) {
        this.name = name;
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
