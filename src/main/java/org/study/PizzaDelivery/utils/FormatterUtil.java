package org.study.PizzaDelivery.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.study.PizzaDelivery.model.Order;

import java.util.Objects;
import java.util.ResourceBundle;

@Component
public class FormatterUtil {

    private static final Logger logger = LogManager.getLogger(FormatterUtil.class);

    String phoneRegEx = "^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$";


    public String getPhoneRegEx() {
        logger.info("Call method: getPhoneRegEx()");
        logger.info("return: " + phoneRegEx);
        return phoneRegEx;
    }

    public String commentWithChangeFormatter(String comment, Double change) {
        logger.info("Call method: commentWithChangeFormatter(comment:" + comment + ", change: " + change + ")");
        ResourceBundle i18nBundle = ResourceBundle.getBundle("i18n/message",
                Objects.requireNonNull(Objects.requireNonNull(LocaleContextHolder.getLocaleContext()).getLocale()));

        return comment + " (" + i18nBundle.getString("change.amount") + " " + change +
                "." + i18nBundle.getString("currency") + ").";
    }

    public String getFormattedHtmlTextOfOrderItemsToMail(Order order) {
        logger.info("Call method: getFormattedHtmlTextOfOrderItemsToMail(order:" + order + ")");

        ResourceBundle i18nBundle = ResourceBundle.getBundle("i18n/message",
                Objects.requireNonNull(Objects.requireNonNull(LocaleContextHolder.getLocaleContext()).getLocale()));

        StringBuilder items = new StringBuilder();
        order.getOrderItems().forEach(i -> items.append("<p><i>")
                .append(i.getProduct().getName())
                .append(" ")
                .append(i.getProduct().getBase().getName().toLowerCase())
                .append(" [")
                .append(i.getComment())
                .append("]")
                .append(" - ")
                .append(i18nBundle.getString("price").toLowerCase())
                .append(": ")
                .append(i.getPrice())
                .append(".")
                .append(i18nBundle.getString("currency"))
                .append(";</i></p>"));

        return items.toString();
    }
}
