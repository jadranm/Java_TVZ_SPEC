package hr.java.production.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Factory factory = (Factory) o;
        return Objects.equals(address, factory.address) && Arrays.equals(items, factory.items);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), address);
        result = 31 * result + Arrays.hashCode(items);
        return result;
    }
}
