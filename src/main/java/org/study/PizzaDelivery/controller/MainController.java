package org.study.PizzaDelivery.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.study.PizzaDelivery.enums.IngredientType;
import org.study.PizzaDelivery.model.User;
import org.study.PizzaDelivery.service.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
@RequestMapping("/")
@SessionAttributes("user")
public class MainController {

    private static final Logger logger = LogManager.getLogger(MainController.class);

    @Autowired
    UserService userService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private BasketService basketService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BaseService baseService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private Environment env;


    @GetMapping("/")
    public String mainPage(HttpSession session, Model model) {
        logger.info("GET request /");

        model.addAttribute("topProducts", productService.findTop3Products().values());

        logger.info("User in HttpSession: " + session.getAttribute("user"));
        if (session.getAttribute("user") == null) {
            if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")
                    && !SecurityContextHolder.getContext().getAuthentication().getName().equals(env.getProperty("admin.login"))) {
                logger.info("Initialize user object in HttpSession");
                String name = SecurityContextHolder.getContext().getAuthentication().getName();
                User user = (User) userService.loadUserByUsername(name);
                session.setAttribute("user", user);
                if (session.getAttribute("user") != null) {
                    logger.info("Set User in session: " + user.toString());
                }
            }
        }

        return "index";
    }

    @GetMapping("/constructor")
    public String constructor(Model model) {
        logger.info("GET request /constructor");

        model.addAttribute("category", categoryService.findByName("Своя"));
        model.addAttribute("bases", baseService.findAll());
        model.addAttribute("ingredients", ingredientService.findAll());
        model.addAttribute("sauces", ingredientService.findByType(IngredientType.SAUCE));
        model.addAttribute("cheeses", ingredientService.findByType(IngredientType.CHEESE));
        model.addAttribute("meat", ingredientService.findByType(IngredientType.MEAT));
        model.addAttribute("seafood", ingredientService.findByType(IngredientType.SEAFOOD));
        model.addAttribute("vegetables", ingredientService.findByType(IngredientType.VEGETABLE));
        model.addAttribute("ingredientTypes", IngredientType.values());
        return "constructor";
    }

    @PostMapping(value = "/constructor")
    public String editProductPage(@ModelAttribute User user,
                                  @RequestParam(defaultValue = "") Short baseId,
                                  @RequestParam(defaultValue = "0") Short sauceId,
                                  @RequestParam(defaultValue = "") short[] ingredientsIds,
                                  Model model) {
        logger.info("POST request /constructor" +
                "[user: " + user +
                ", baseId: " + baseId +
                ", sauceId: " + sauceId +
                ", ingredientsIds: " + Arrays.toString(ingredientsIds) + "]");

        basketService.addCustomProductToBasket(user, baseId, sauceId, ingredientsIds);

        return "redirect:/category";
    }

    @GetMapping("/onlinePayment")
    public String onlinePayment(Model model) {
        logger.info("GET request /onlinePayment");
        if (model.getAttribute("orderId") == null) {

        }

        return "onlinePayment";
    }

    @PostMapping("/onlinePayment")
    public String onlinePaymentSuccessful(@ModelAttribute("orderId") Long orderId) {
        logger.info("POST request /onlinePayment [orderId: " + orderId + "]");

        orderService.paidUpOrder(orderId);

        return "redirect:/user";
    }
}
