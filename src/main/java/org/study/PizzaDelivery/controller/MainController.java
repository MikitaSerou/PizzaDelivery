package org.study.PizzaDelivery.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.study.PizzaDelivery.data.model.User;
import org.study.PizzaDelivery.data.service.UserService;

import javax.servlet.http.HttpSession;



@Controller
@RequestMapping("/")
@SessionAttributes("user")
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String mainPage(HttpSession session){
        if (session.getAttribute("id") == null) {
            if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
                String name = SecurityContextHolder.getContext().getAuthentication().getName();
                User user = userService.findByName(name);
                session.setAttribute("user", user);
            }
        }
        return "index";
    }
}
