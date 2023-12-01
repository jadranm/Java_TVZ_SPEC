package hr.java.production.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja trgovinu s nazivom, web adresom i popisom stavki koje prodaje.
 */
public class Store extends NamedEntity{

    String webAddress;
    List<Item> items;

    public Store(String name, Integer id, String webAddress, List<Item> items) {
        super(name, id);
        this.webAddress = webAddress;
        this.items = items;
    }

    /**
     * Konstruktor za inicijalizaciju trgovine sa zadanim nazivom, web adresom i popisom stavki.
     *
     * @param name Naziv trgovine.
     * @param webAddress Web adresa trgovine.
     * @param items Popis stavki koje trgovina prodaje.
     */




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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Store store = (Store) o;
        return Objects.equals(webAddress, store.webAddress) && Objects.equals(items, store.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), webAddress, items);
    }
}
