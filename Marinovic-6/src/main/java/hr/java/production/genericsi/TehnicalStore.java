package hr.java.production.genericsi;

import hr.java.production.model.Item;
import hr.java.production.model.Store;
import hr.java.production.model.Tehnical;

import java.util.List;

public class TehnicalStore<T extends Tehnical> extends Store {
    /**
     * Konstruktor za inicijalizaciju trgovine sa zadanim nazivom, web adresom i popisom stavki.
     *
     * @param name       Naziv trgovine.
     * @param webAddress Web adresa trgovine.
     * @param items      Popis stavki koje trgovina prodaje.
     */

    public TehnicalStore(String name, Integer id, String webAddress, List<Item> items) {
        super(name, id, webAddress, items);
    }
}
