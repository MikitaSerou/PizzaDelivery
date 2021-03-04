package org.study.PizzaDelivery.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import org.study.PizzaDelivery.model.Order;
import org.study.PizzaDelivery.model.User;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.File;
import java.util.ResourceBundle;

@Service
public class EmailService {

    private static final Logger logger = LogManager.getLogger(EmailService.class);

    @Autowired
    private Environment env;

    @Autowired
    private JavaMailSender mailSender;


    public void sendRegistrationSuccessfulMail(User user) {
        logger.info("Call method: sendRegistrationSuccessfulMail(user:" + user + ")");
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                ResourceBundle i18nBundle = ResourceBundle.getBundle("i18n/message", LocaleContextHolder.getLocaleContext().getLocale());
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getMail()));
                mimeMessage.setFrom(new InternetAddress(env.getProperty("email.sender")));
                mimeMessage.setSubject(i18nBundle.getString("mail.successful.registration"));

                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                helper.setText("<html><body><img src='cid:logo'>" +
                        "<h1>" + i18nBundle.getString("mail.greetings") + " " + user.getUsername() + "!</h1>" +
                        "<p>" + i18nBundle.getString("mail.thanks") + "</p>" +
                        "<p>" + i18nBundle.getString("mail.id.appropriation") + " " + user.getId() + "</p>" +
                        "<p>" + i18nBundle.getString("mail.phone") + " " + user.getPhoneNumber() + "</p><br/>" +
                        "<i>" + i18nBundle.getString("mail.conclusion") + "</i>" +
                        "</body></html>", true);


                FileSystemResource res = new FileSystemResource(
                        new File(getClass().getResource("/").getPath() + "/email/logoBlack.png"));
                helper.addInline("logo", res);

            }
        };

        try {
            mailSender.send(preparator);
        } catch (MailException ex) {
            logger.error(ex.getMessage());
        }
    }

    public void sendOrderInfoMessage(User user, Order order) {
        logger.info("Call method: sendOrderInfoMessage(user:" + user +
                ", order:" + order + ")");

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                ResourceBundle i18nBundle = ResourceBundle.getBundle("i18n/message",
                        LocaleContextHolder.getLocaleContext().getLocale());

                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getMail()));
                mimeMessage.setFrom(new InternetAddress(env.getProperty("email.sender")));
                mimeMessage.setSubject(i18nBundle.getString("mail.subject.order"));

                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                StringBuilder items = new StringBuilder();
                order.getOrderItems().forEach(i -> {
                    items.append("<p><i>" + i.getProduct().getName() +
                            " " + i.getProduct().getBase().getName().toLowerCase() +
                            " [" + i.getComment() + "]" +
                            " - " + i18nBundle.getString("price").toLowerCase() + ": " + i.getPrice() +
                            "." + i18nBundle.getString("currency") + ";</i></p>");
                });

                helper.setText("<html><body><img src='cid:logo'>" +
                        "<h1>" + i18nBundle.getString("mail.order.thanks") + " " + user.getUsername() + "!</h1>" +
                        "<p>" + i18nBundle.getString("mail.order.id") + " " + order.getId() + "</p>" +
                        "<p>" + i18nBundle.getString("mail.order.time") + " " + order.getTime() + "</p>" +
                        "<fieldset><h3>" + i18nBundle.getString("mail.order.items") + "</h3>" +
                        items.toString() + "</fieldset>" +
                        "<p>" + i18nBundle.getString("mail.order.phone") + " " + order.getPhoneNumber() + "</p>" +
                        "<p>" + i18nBundle.getString("mail.order.comment") + " " + order.getComment() + "</p>" +
                        "<p>" + i18nBundle.getString("mail.order.price") + " " + order.getPrice() +
                        "." + i18nBundle.getString("currency") + "</p>" +
                        "<p>" + i18nBundle.getString("mail.order.typeOfPayment") +
                        " " + i18nBundle.getString(order.getTypeOfPayment().toString()) + "</p><br/>" +
                        "<h2><i>" + i18nBundle.getString("mail.order.conclusion") + "</i></h2>" +
                        "</body></html>", true);

                FileSystemResource res = new FileSystemResource(
                        new File(getClass().getResource("/").getPath() + "/email/logoBlack.png"));
                helper.addInline("logo", res);
            }
        };

        try {
            mailSender.send(preparator);
        } catch (
                MailException ex) {
            logger.error(ex.getMessage());
        }
    }
}
