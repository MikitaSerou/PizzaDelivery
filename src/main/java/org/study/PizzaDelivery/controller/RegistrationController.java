package org.study.PizzaDelivery.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.study.PizzaDelivery.data.model.User;
import org.study.PizzaDelivery.data.service.UserService;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;


    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm,
                          BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Passwords not match");
            return "registration";
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "User with this name is already exist");
            return "registration";
        }
        return "redirect:/";
    }
}