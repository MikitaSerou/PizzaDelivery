package org.study.PizzaDelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.study.PizzaDelivery.data.enums.TypeOfPayment;
import org.study.PizzaDelivery.data.model.Basket;
import org.study.PizzaDelivery.data.model.User;
import org.study.PizzaDelivery.data.service.BasketItemService;
import org.study.PizzaDelivery.data.service.BasketService;
import org.study.PizzaDelivery.data.service.OrderService;
import org.study.PizzaDelivery.data.service.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {
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
        model.addAttribute("userOrders", orderService.findOrdersByUserId(user.getId()));
        return "user/cabinet";
    }

    @GetMapping("/basket")
    @Transactional
    public String basket(@ModelAttribute User user, Model model) {
        Basket basket =basketService.findActiveByUserID(user.getId());
        model.addAttribute("basket", basket);
        System.err.println("basket when you wisit page: " + basket);
        System.err.println("basketITEMS when you wisit page: " + basket.getBasketItems().toString());
        model.addAttribute("basketSum", basketService.calculatePrice(basket.getId()));
        System.err.println("basketITEMS after calculate: " + basket.getBasketItems().toString());
        model.addAttribute("typesOfPayment", TypeOfPayment.values());
        return "user/basket";
    }

    @PostMapping("/basket")
    public String basketActivity(@ModelAttribute User user,
                                 @RequestParam(required = true, defaultValue = "") Long basketId,
                                 @RequestParam(required = true, defaultValue = "") Long itemId,
                                 @RequestParam(required = true, defaultValue = "") String action,
                                 @RequestParam(required = true, defaultValue = "") String comment,
                                 @RequestParam(required = true, defaultValue = "") String phoneNumber,
                                 @RequestParam(required = true, defaultValue = "") TypeOfPayment typeOfPayment,
                                 Model model) {

        if (action.equals("deleteItem")) {
            System.out.println("DELETE ITEM");
            basketItemService.deleteItem(itemId);
        }
        if (action.equals("clear")) {
            System.out.println("Clear basket: " + basketId);
            basketService.clearBasket(basketService.getActiveBasketByUserId(user.getId()));
        }
        if (action.equals("submit")) {
            Basket basket = basketService.getActiveBasketByUserId(user.getId());
            System.err.println("Before submit: " + basketId +" "+ phoneNumber +" "+ comment  +" "+ typeOfPayment );
            System.err.println("Before submit items: " +basket.getBasketItems().toString());//norm
            //TODO тут все идет наперексяк. уже 3 айтема смотреть представление, возможно что-то в посте


            orderService.addOrder(basket, phoneNumber, comment, typeOfPayment);
        return "redirect:/user";
        }

        return "redirect:/user/basket";
    }

}
