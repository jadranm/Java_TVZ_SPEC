package hr.java.production.model;

import java.math.BigDecimal;
/**
 * Predstavlja jabuku koja je jestiva i nasljeđuje osobine predmeta (Item) i sučelja Edible.
 */
public class Apple extends Item implements Edible{

    /**
     * Konstanta koja predstavlja broj kalorija po kilogramu jabuke.
     */
    public static final Integer CALORIES_PER_KILOGRAM = 300;
    BigDecimal weight;

    /**
     * Konstruktor za inicijalizaciju jabuke sa zadanim podacima.
     *
     * @param name Naziv jabuke.
     * @param category Kategorija jabuke.
     * @param width Širina jabuke.
     * @param height Visina jabuke.
     * @param lenght Duljina jabuke.
     * @param productionCost Trošak proizvodnje jabuke.
     * @param sellingPrice Prodajna cijena jabuke.
     * @param discountAmount Popust na cijenu jabuke.
     * @param weight Težina jabuke.
     */

    public Apple(String name, Integer id, Category category, BigDecimal width, BigDecimal height, BigDecimal lenght, BigDecimal productionCost, BigDecimal sellingPrice, BigDecimal discountAmount, BigDecimal weight) {
        super(name, id, category, width, height, lenght, productionCost, sellingPrice, discountAmount);
        this.weight = weight;
    }

    /**
     * Računa broj kilokalorija za ovu jestivu stavku na temelju njezine težine.
     *
     * @return Broj kilokalorija za ovu jestivu stavku.
     */
    @Override
    public BigDecimal calculateCalories() {
        return weight.multiply(BigDecimal.valueOf(CALORIES_PER_KILOGRAM));
    }


    /**
     * Računa cijenu za ovu jestivu stavku na temelju težine, prodajne cijene i popusta.
     *
     * @return Ukupna cijena za ovu jestivu stavku.
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
}
