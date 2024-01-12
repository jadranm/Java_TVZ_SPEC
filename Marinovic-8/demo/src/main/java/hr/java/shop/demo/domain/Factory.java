package hr.java.shop.demo.domain;

import java.util.List;
import java.util.Objects;

/**
 * Predstavlja tvornicu s nazivom, adresom i popisom stavki koje proizvodi.
 */
public class Factory extends NamedEntity {

    Address address;
    List<Item> items;

    public Factory(String name, Integer id, Address address, List<Item> items) {
        super(name, id);
        this.address = address;
        this.items = items;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * Konstruktor za inicijalizaciju tvornice sa zadanim nazivom, adresom i popisom stavki.
     *
     * @param name Naziv tvornice.
     * @param address Adresa tvornice.
     * @param items Popis stavki koje tvornica proizvodi.
     */


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Factory factory = (Factory) o;
        return Objects.equals(address, factory.address) && Objects.equals(items, factory.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address, items);
    }
}
