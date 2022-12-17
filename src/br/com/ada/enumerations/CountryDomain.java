package br.com.ada.enumerations;
public enum CountryDomain {

    AFRICA_DO_SUL("África do Sul", "za"),
    ALBANIA("Albânia", ".al"),
    ANGOLA("Angola", ".al"),
    ARGENTINA("Argentina", ".ar"),
    AUSTRALIA("Australia", ".au"),
    AUSTRIA("Áustria", ".com.at"),
    BRASIL("Brasil", ".br"),
    FRANCA("França", ".fr"),
    PORTUGAL("Portugal", ".pt");

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
