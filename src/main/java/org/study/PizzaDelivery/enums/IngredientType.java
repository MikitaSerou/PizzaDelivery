package org.study.PizzaDelivery.enums;

public enum IngredientType {
    CHEESE("Cheese"),
    MEAT("Meat"),
    VEGETABLE("Vegetable"),
    SEAFOOD("Seafood"),
    SAUCE("Sauce");

    private final String str;

    IngredientType(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
