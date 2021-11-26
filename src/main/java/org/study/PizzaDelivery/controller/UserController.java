package org.study.PizzaDelivery.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.study.PizzaDelivery.enums.TypeOfPayment;
import org.study.PizzaDelivery.model.Basket;
import org.study.PizzaDelivery.model.Order;
import org.study.PizzaDelivery.model.User;
import org.study.PizzaDelivery.service.BasketItemService;
import org.study.PizzaDelivery.service.BasketService;
import org.study.PizzaDelivery.service.EmailService;
import org.study.PizzaDelivery.service.OrderService;
import org.study.PizzaDelivery.utils.FormatterUtil;

import java.util.regex.Pattern;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final OrderService orderService;

    private final BasketService basketService;

    private final BasketItemService basketItemService;

    private final FormatterUtil formatterUtil;

    private final EmailService emailService;

    @Autowired
    public UserController(OrderService orderService, BasketService basketService, BasketItemService basketItemService,
                          FormatterUtil formatterUtil, EmailService emailService) {
        this.orderService = orderService;
        this.basketService = basketService;
        this.basketItemService = basketItemService;
        this.formatterUtil = formatterUtil;
        this.emailService = emailService;
    }

    @GetMapping
    public String account(@ModelAttribute User user, Model model) {
        logger.info("GET request /user");
        model.addAttribute("userOrders", orderService.findOrdersByUserId(user.getId()));

        return "user/ordersHistory";
    }

    @GetMapping("/basket")
    @Transactional
    public String basket(@ModelAttribute User user, Model model) {
        logger.info("GET request /user/basket");

        Basket basket = basketService.findActiveByUserID(user.getId());
        model.addAttribute("basket", basket);
        model.addAttribute("basketSum", basketService.calculatePrice(basket.getId()));
        model.addAttribute("typesOfPayment", TypeOfPayment.values());

        return "user/basket";
    }


    @PostMapping("/basket")
    public String basketActivity(@ModelAttribute User user,
                                 @RequestParam(defaultValue = "") Long itemId,
                                 @RequestParam(defaultValue = "") String action,
                                 @RequestParam(defaultValue = "") String comment,
                                 @RequestParam(defaultValue = "") String orderPhoneNumber,
                                 @RequestParam(defaultValue = "") Double change,
                                 @RequestParam(defaultValue = "") TypeOfPayment typeOfPayment,
                                 Model model) {
        logger.info("POST request /user/basket" +
                "[user: " + user +
                ", itemId: " + itemId +
                ", orderPhoneNumber: " + orderPhoneNumber +
                ", comment: " + comment +
                ", typeOfPayment: " + typeOfPayment +
                ", action: " + action +
                ", change:" + change + "]");

        if (action.equals("deleteItem")) {
            basketItemService.deleteItem(itemId);
        }
        if (action.equals("clear")) {
            basketService.clearBasket(basketService.findActiveByUserID(user.getId()));
        }
        if (action.equals("submit")) {
            if (orderPhoneNumber.equals("") ||
                    !Pattern.matches(formatterUtil.getPhoneRegEx(), orderPhoneNumber)) {
                logger.error("Errors in form. Phone number not specified.");
                model.addAttribute("phoneError", "user.phoneNumber.empty");
                return this.basket(user, model);
            }
            if (change != null) {
                comment = formatterUtil.commentWithChangeFormatter(comment, change);
            }

            Order notPaidOrder = orderService.addOrder(user, orderPhoneNumber, comment, typeOfPayment);
           // emailService.sendOrderInfoMessage(user, orderService.findLastOrderOfUserByUserId(user.getId()));

            if (typeOfPayment.equals(TypeOfPayment.ONLINE)) {
                model.addAttribute("orderId", notPaidOrder.getId());
                model.addAttribute("orderSum", notPaidOrder.getPrice());
                return "onlinePayment";
            }

            return "redirect:/user";
        }

        return "redirect:/user/basket";
    }
}
