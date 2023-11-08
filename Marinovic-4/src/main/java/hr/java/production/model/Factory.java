package hr.java.production.model;

import java.util.List;

/**
 * Predstavlja tvornicu s nazivom, adresom i popisom stavki koje proizvodi.
 */
public class Factory extends NamedEntity {

    Address address;
    Item[] items;

    /**
     * Konstruktor za inicijalizaciju tvornice sa zadanim nazivom, adresom i popisom stavki.
     *
     * @param name Naziv tvornice.
     * @param address Adresa tvornice.
     * @param items Popis stavki koje tvornica proizvodi.
     */
    public Factory(String name, Address address, List<Item> items) {
        super(name);
        this.address = address;
        this.items = items.toArray(new Item[0]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }
}
