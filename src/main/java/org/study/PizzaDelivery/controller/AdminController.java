package org.study.PizzaDelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.study.PizzaDelivery.data.service.IngredientService;
import org.study.PizzaDelivery.data.service.OrderService;
import org.study.PizzaDelivery.data.service.UserService;
import org.study.PizzaDelivery.data.enums.Type;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private OrderService orderService;


    @GetMapping
    public String cabinet() {
        return "admin/adminOffice";
    }


    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin/users";
    }

    @PostMapping
    public String deleteUser(@RequestParam(defaultValue = "") Long userId,
                             @RequestParam(defaultValue = "") String action) {
        if (action.equals("delete")) {
            userService.deleteUser(userId);
        }
        return "redirect:/admin/users";
    }


    @GetMapping("/users/{userId}")
    public String showUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("user", userService.findUserById(userId));
        model.addAttribute("userOrders", orderService.findOrdersByUserId(userId));
        return "admin/userOrders";
    }

    @GetMapping("/orders")
    public String activeOrdersList(Model model) {
        model.addAttribute("activeOrders", orderService.findNotPaidOrders());
        return "admin/orders";
    }

    @PostMapping("/orders")
    public String changeOrderStatus(@RequestParam(required = true, defaultValue = "") Long orderId,
                                    @RequestParam(required = true, defaultValue = "") String action,
                                    Model model) {

        if (action.equals("cancel")) {
            orderService.cancelOrder(orderId);
        }

        if (action.equals("paidUp")) {
            orderService.paidUpOrder(orderId);
        }

        if (action.equals("notPaid")) {
            orderService.setOrdernotPaidStatus(orderId);
        }

        return "redirect:/admin/orders";
    }

    @GetMapping("/ingredients")
    public String ingredientsList(Model model) {
        model.addAttribute("ingredients", ingredientService.findAll());
        model.addAttribute("types", Type.values());
        return "admin/ingredients";
    }

    @PostMapping("/ingredients")
    public String ingredients(@RequestParam(required = true, defaultValue = "") Short ingredientId,
                              @RequestParam(required = true, defaultValue = "") String ingredientName,
                              @RequestParam(required = true, defaultValue = "") Double ingredientPrice,
                              @RequestParam(required = true, defaultValue = "") Type ingredientType,
                              @RequestParam(required = true, defaultValue = "") String action,
                              Model model) {

        if (action.equals("delete")) {
            ingredientService.deleteIngredient(ingredientId);
        }

        if (action.equals("add")) {
            ingredientService.addIngredient(ingredientName, ingredientPrice, ingredientType);

        }

        if (action.equals("edit")) {
            ingredientService.updateIngredient(ingredientId, ingredientName, ingredientPrice, ingredientType);
        }

        return "redirect:/admin/ingredients";
    }
}

