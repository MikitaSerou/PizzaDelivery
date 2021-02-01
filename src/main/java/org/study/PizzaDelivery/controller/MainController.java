package org.study.PizzaDelivery.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.PizzaDelivery.data.model.User;


@Controller
@RequestMapping("/")
public class MainController {


    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("userForm", new User());

        return "index";
    }
}
