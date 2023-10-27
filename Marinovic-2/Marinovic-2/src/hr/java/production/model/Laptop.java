package hr.java.production.model;

import java.math.BigDecimal;

public final class Laptop extends Item implements Tehnical {

    Integer warrantyPeriodInMonths;
    public Laptop(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal lenght, BigDecimal productionCost, BigDecimal sellingPrice, BigDecimal discount, Integer warantyPeriodInMonths) {
        super(name, category, width, height, lenght, productionCost, sellingPrice, discount);
        this.warrantyPeriodInMonths = warantyPeriodInMonths;
    }

    @Override
    public Integer getWarrantyDuration() {
        return warrantyPeriodInMonths;
    }
}
