package org.study.PizzaDelivery.utils;

import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class Formatter {
    String phoneRegEx = "^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$";


    public String getPhoneRegEx() {
        return phoneRegEx;
    }

}
