package org.study.PizzaDelivery.data.enums;

public enum Status {
    NOT_PAID("Not paid"),
    PAID("Paid"),
    CANCELED("Canceled");

    private final String str;

    Status(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
