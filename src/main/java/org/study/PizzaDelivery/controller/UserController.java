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
import org.study.PizzaDelivery.model.User;
import org.study.PizzaDelivery.service.BasketItemService;
import org.study.PizzaDelivery.service.BasketService;
import org.study.PizzaDelivery.service.OrderService;
import org.study.PizzaDelivery.service.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BasketService basketService;

    @Autowired
    private BasketItemService basketItemService;


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
                                 @RequestParam(defaultValue = "") String phoneNumber,
                                 @RequestParam(defaultValue = "") TypeOfPayment typeOfPayment,
                                 Model model) {
        logger.info("POST request /user/basket" +
                "[user: " + user +
                ", itemId: " + itemId +
                ", phoneNumber: " + phoneNumber +
                ", comment: " + comment +
                ", typeOfPayment: " + typeOfPayment +
                ", action: " + action + "]");

        if (action.equals("deleteItem")) {
            basketItemService.deleteItem(itemId);
        }
        if (action.equals("clear")) {
            basketService.clearBasket(basketService.findActiveByUserID(user.getId()));
        }
        if (action.equals("submit")) {
            orderService.addOrder(user, phoneNumber, comment, typeOfPayment);
            return "redirect:/user";
        }

        return "redirect:/user/basket";
    }

}
