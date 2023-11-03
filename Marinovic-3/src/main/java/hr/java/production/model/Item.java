package hr.java.production.model;

import java.math.BigDecimal;

/**
 * Predstavlja opću stavku s nazivom, kategorijom, dimenzijama, troškom proizvodnje, prodajnom cijenom i popustom.
 */
public class Item extends NamedEntity {

    Category category;
    BigDecimal width;
    BigDecimal height;
    BigDecimal lenght;
    BigDecimal productionCost;
    BigDecimal sellingPrice;
    BigDecimal discountAmount;

    /**
     * Konstruktor za inicijalizaciju stavke sa zadanim nazivom, kategorijom, dimenzijama, troškom proizvodnje,
     * prodajnom cijenom i popustom.
     *
     * @param name Naziv stavke.
     * @param category Kategorija stavke.
     * @param width Širina stavke.
     * @param height Visina stavke.
     * @param lenght Duljina stavke.
     * @param productionCost Trošak proizvodnje stavke.
     * @param sellingPrice Prodajna cijena stavke.
     * @param discount Popust na cijenu stavke.
     */

    public Item(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal lenght, BigDecimal productionCost, BigDecimal sellingPrice, BigDecimal discount) {
        super(name);
        this.category = category;
        this.width = width;
        this.height = height;
        this.lenght = lenght;
        this.productionCost = productionCost;
        this.sellingPrice = sellingPrice;
        this.discountAmount = discount;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getLenght() {
        return lenght;
    }

    public void setLenght(BigDecimal lenght) {
        this.lenght = lenght;
    }

    public BigDecimal getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(BigDecimal productionCost) {
        this.productionCost = productionCost;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

}