package org.study.PizzaDelivery.utils;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

@Component
public class Formatter {

    String phoneRegEx = "^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$";


    public String getPhoneRegEx() {
        return phoneRegEx;
    }

    public String commentWithChangeFormatter(String comment, Double change) {
        ResourceBundle i18nBundle = ResourceBundle.getBundle("i18n/message",
                LocaleContextHolder.getLocaleContext().getLocale());

        return comment + " (" + i18nBundle.getString("change.amount") + " " + change +
                "." + i18nBundle.getString("currency") + ").";
    }
}
