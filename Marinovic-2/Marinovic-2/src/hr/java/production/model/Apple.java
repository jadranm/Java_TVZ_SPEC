package hr.java.production.model;

import java.math.BigDecimal;

public class Apple extends Item implements Edible{

    public static final Integer CALORIES_PER_KILOGRAM = 300;
    BigDecimal weight;

    public Apple(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal lenght, BigDecimal productionCost, BigDecimal sellingPrice,BigDecimal discount, BigDecimal weight) {
        super(name, category, width, height, lenght, productionCost, sellingPrice, discount);
        this.weight = weight;
    }

    @Override
    public BigDecimal calculateCalories() {
        return weight.multiply(BigDecimal.valueOf(CALORIES_PER_KILOGRAM));
    }

    @Override
    public BigDecimal calculatePrice() {
        return weight.multiply(getSellingPrice());
    }
}
