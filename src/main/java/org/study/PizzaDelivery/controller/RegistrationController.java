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
import org.study.PizzaDelivery.service.EmailService;
import org.study.PizzaDelivery.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private static final Logger logger = LogManager.getLogger(RegistrationController.class);

    private final UserService userService;

    private final EmailService emailService;

    @Autowired
    public RegistrationController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

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
            logger.error("Errors in form \"registrationForm (passwords not match)\"");
            model.addAttribute("matchError", "password.match.error");
            return "registration";
        }
        if (!userService.saveUser(registrationForm)) {
            logger.error("Errors in form \"registrationForm (user already exist)\"");
            model.addAttribute("uniqueError", "username.unique.error");
            return "registration";
        }

        emailService.sendRegistrationSuccessfulMail(registrationForm);

        return "redirect:/";
    }
}
