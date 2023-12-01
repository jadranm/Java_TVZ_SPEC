package hr.java.production.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Predstavlja opću stavku s nazivom, kategorijom, dimenzijama, troškom proizvodnje, prodajnom cijenom i popustom.
 */
public class Item extends NamedEntity implements Serializable {

    Category category;
    BigDecimal width;
    BigDecimal height;
    BigDecimal lenght;
    BigDecimal productionCost;
    BigDecimal sellingPrice;
    BigDecimal discountAmount;

    public Item(String name, Integer id, Category category, BigDecimal width, BigDecimal height, BigDecimal lenght, BigDecimal productionCost, BigDecimal sellingPrice, BigDecimal discountAmount) {
        super(name, id);
        this.category = category;
        this.width = width;
        this.height = height;
        this.lenght = lenght;
        this.productionCost = productionCost;
        this.sellingPrice = sellingPrice;
        this.discountAmount = discountAmount;
    }

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
     * @param discountAmount Popust na cijenu stavke.
     */



    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getVolume(){
        return this.width.multiply(this.height.multiply(this.lenght));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Item item = (Item) o;
        return Objects.equals(category, item.category) && Objects.equals(width, item.width) && Objects.equals(height, item.height) && Objects.equals(lenght, item.lenght) && Objects.equals(productionCost, item.productionCost) && Objects.equals(sellingPrice, item.sellingPrice) && Objects.equals(discountAmount, item.discountAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), category, width, height, lenght, productionCost, sellingPrice, discountAmount);
    }

    @Override
    public String toString() {
        return "Item{" +
                "category=" + category +
                ", width=" + width +
                ", height=" + height +
                ", lenght=" + lenght +
                ", productionCost=" + productionCost +
                ", sellingPrice=" + sellingPrice +
                ", discountAmount=" + discountAmount +
                ", name='" + name + '\'' +
                '}';
    }
}