package org.study.PizzaDelivery.enums;

public enum TypeOfPayment {
    CASH("Cash"),
    CARD("Card"),
    ONLINE("Online");

    private final String str;

    TypeOfPayment(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
