package org.study.PizzaDelivery.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.PizzaDelivery.model.User;
import org.study.PizzaDelivery.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private static final Logger logger = LogManager.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;


    @GetMapping
    public String registration(Model model) {
        logger.info("GET request /registration");

        model.addAttribute("registrationForm", new User());

        return "registration";
    }

    @PostMapping
    public String addUser(@ModelAttribute("registrationForm") @Valid User registrationForm,
                          BindingResult bindingResult, Model model) {
        logger.info("POST request /registration" +
                "[registrationForm: " + registrationForm + "]");

        if (bindingResult.hasErrors()) {
            logger.error("Errors in form \"registrationForm\"");
            return "registration";
        }
        if (!registrationForm.getPassword().equals(registrationForm.getPasswordConfirm())) {
            model.addAttribute("matchError", "password.match.error");
            return "registration";
        }
        if (!userService.saveUser(registrationForm)) {
            model.addAttribute("uniqueError", "username.unique.error");
            return "registration";
        }
        return "redirect:/";
    }
}
