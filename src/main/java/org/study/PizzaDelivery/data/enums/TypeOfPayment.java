package org.study.PizzaDelivery.data.enums;

public enum TypeOfPayment {
    CASH("CASH"),
    CARD("CARD"),
    ONLINE("ONLINE");

    private final String str;

    TypeOfPayment(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
