package org.study.PizzaDelivery.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.PizzaDelivery.model.User;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = LogManager.getLogger(LoginController.class);
    @GetMapping
    public String getPage() {
        logger.info("GET request /login");
        return "login";
    }

    @PostMapping
    public String login(@ModelAttribute("loginForm") @Valid User loginForm,
                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        logger.info("POST request /registration" +
                "[loginForm: " + loginForm + "]");
        return "redirect:/";
    }
}
