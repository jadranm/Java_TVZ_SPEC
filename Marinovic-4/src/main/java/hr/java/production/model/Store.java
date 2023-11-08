package hr.java.production.model;

import java.util.List;

/**
 * Predstavlja trgovinu s nazivom, web adresom i popisom stavki koje prodaje.
 */
public class Store extends NamedEntity{

    String webAddress;
    List<Item> items;

    /**
     * Konstruktor za inicijalizaciju trgovine sa zadanim nazivom, web adresom i popisom stavki.
     *
     * @param name Naziv trgovine.
     * @param webAddress Web adresa trgovine.
     * @param items Popis stavki koje trgovina prodaje.
     */
    public Store(String name, String webAddress, List<Item> items) {
        super(name);
        this.webAddress = webAddress;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
