package hr.java.production.model;

import java.math.BigDecimal;

public class Item extends NamedEntity {

    Category category;
    BigDecimal width;
    BigDecimal height;
    BigDecimal lenght;
    BigDecimal productionCost;
    BigDecimal sellingPrice;
    BigDecimal discountAmount;


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