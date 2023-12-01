package hr.java.production.model;

import java.math.BigDecimal;

/**
 * Predstavlja pšenicu kao jestivu stavku.
 */
public class Wheat extends Item implements Edible {

    public static final Integer CALORIES_PER_KILOGRAM = 500;
    BigDecimal weight;

    /**
     * Konstruktor za inicijalizaciju pšenice sa zadanim podacima.
     *
     * @param name Naziv pšenice.
     * @param category Kategorija pšenice.
     * @param width Širina pšenice.
     * @param height Visina pšenice.
     * @param lenght Duljina pšenice.
     * @param productionCost Trošak proizvodnje pšenice.
     * @param sellingPrice Prodajna cijena pšenice.
     * @param discount Popust na cijenu pšenice.
     * @param weight Težina pšenice u kilogramima.
     */
    public Wheat(String name, Integer id, Category category, BigDecimal width, BigDecimal height, BigDecimal lenght, BigDecimal productionCost, BigDecimal sellingPrice, BigDecimal discountAmount, BigDecimal weight) {
        super(name, id, category, width, height, lenght, productionCost, sellingPrice, discountAmount);
        this.weight = weight;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal calculateCalories() {
        return weight.multiply(BigDecimal.valueOf(CALORIES_PER_KILOGRAM));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal calculatePrice() {
        if (discountAmount.equals(BigDecimal.ZERO)) {
            return sellingPrice.multiply(weight);

        }else {
            BigDecimal discountedPrice = sellingPrice.subtract(sellingPrice.multiply(discountAmount.divide(BigDecimal.valueOf(100))));
            return discountedPrice.multiply(weight);
        }
    }

    @Override
    public String toString() {
        return "Wheat{" +
                "weight=" + weight +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", width=" + width +
                ", height=" + height +
                ", lenght=" + lenght +
                ", productionCost=" + productionCost +
                ", sellingPrice=" + sellingPrice +
                ", name='" + name + '\'' +
                '}';
    }
}
