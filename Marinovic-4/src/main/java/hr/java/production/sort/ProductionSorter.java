package hr.java.production.sort;

import hr.java.production.model.Item;

import java.util.Comparator;

public class ProductionSorter implements Comparator<Item> {

    private final boolean ascendingOrder;

    public ProductionSorter(boolean ascendingOrder) {
        this.ascendingOrder = ascendingOrder;
    }

    @Override
    public int compare(Item item1, Item item2) {

        if (ascendingOrder) {
            return item1.getSellingPrice().compareTo(item2.getSellingPrice());

        }else {
            return item2.getSellingPrice().compareTo(item1.getSellingPrice());
        }
    }

}
