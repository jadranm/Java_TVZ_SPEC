package hr.java.production.genericsi;

import hr.java.production.model.Edible;
import hr.java.production.model.Item;
import hr.java.production.model.Store;

import java.util.List;

public class FoodStore <T extends Edible> extends Store {
    /**
     * Konstruktor za inicijalizaciju trgovine sa zadanim nazivom, web adresom i popisom stavki.
     *
     * @param name       Naziv trgovine.
     * @param webAddress Web adresa trgovine.
     * @param items      Popis stavki koje trgovina prodaje.
     */


    public FoodStore(String name, Integer id, String webAddress, List<Item> items) {
        super(name, id, webAddress, items);
    }
}
