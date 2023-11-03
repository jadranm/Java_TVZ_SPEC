package hr.java.production.model;

import java.math.BigDecimal;

/**
 * Predstavlja laptop kao specifičnu vrstu stavke koja je tehnička oprema.
 * Nasljeđuje osobine klase Item i implementira sučelje Technical.
 */
public final class Laptop extends Item implements Tehnical {

    Integer warrantyPeriodInMonths;

    /**
     * Konstruktor za inicijalizaciju laptopa sa zadanim podacima.
     *
     * @param name Naziv laptopa.
     * @param category Kategorija laptopa.
     * @param width Širina laptopa.
     * @param height Visina laptopa.
     * @param lenght Duljina laptopa.
     * @param productionCost Trošak proizvodnje laptopa.
     * @param sellingPrice Prodajna cijena laptopa.
     * @param discount Popust na cijenu laptopa.
     * @param warantyPeriodInMonths Trajanje jamstva laptopa u mjesecima.
     */
    public Laptop(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal lenght, BigDecimal productionCost, BigDecimal sellingPrice, BigDecimal discount, Integer warantyPeriodInMonths) {
        super(name, category, width, height, lenght, productionCost, sellingPrice, discount);
        this.warrantyPeriodInMonths = warantyPeriodInMonths;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getWarrantyDuration() {
        return warrantyPeriodInMonths;
    }
}
