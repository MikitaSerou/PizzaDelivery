package org.study.PizzaDelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.study.PizzaDelivery.data.model.User;
import org.study.PizzaDelivery.data.service.BasketService;
import org.study.PizzaDelivery.data.service.OrderService;
import org.study.PizzaDelivery.data.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    BasketService basketService;

    // TODO переписать под id
    @GetMapping("/{userName}")
    public String account(@PathVariable("userName") String userName, Model model) {
        //затычка/костыль, чтобы вывести  на представление
        model.addAttribute("user", userService.findByName(userName));
        model.addAttribute("userOrders", orderService.findAllByUserUsername(userName));
        return "user/cabinet";
    }

    // TODO переписать под id
    @GetMapping("/basket/{userName}")
    @Transactional
    public String basket(@PathVariable("userName") String userName, Model model) {
        User user = userService.findByName(userName);
        model.addAttribute("user", user);
        model.addAttribute("basket", basketService.findActiveByUserID(user.getId()));
        return "user/basket";
    }


    @PostMapping("/basket/{userName}")
    public String basketEditOrSubmit(@RequestParam(required = true, defaultValue = "") String userName,
                                     @RequestParam(required = true, defaultValue = "") Long basketId,
                                     @RequestParam(required = true, defaultValue = "") Long itemId,
                                     @RequestParam(required = true, defaultValue = "") String action,
                                     Model model) {

        if (action.equals("delete")) {

        }

        if (action.equals("clear")) {
            System.out.println("Clear basket: " + basketId);
        basketService.clearBasket(basketId);
        }

        if (action.equals("submit")) {

        }

        return "redirect:/user/basket/"+ userName;
    }

}
