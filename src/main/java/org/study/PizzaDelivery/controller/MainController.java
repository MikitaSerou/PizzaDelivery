package org.study.PizzaDelivery.controller;





import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.study.PizzaDelivery.data.enums.IngredientType;
import org.study.PizzaDelivery.data.model.User;
import org.study.PizzaDelivery.data.service.*;

import javax.servlet.http.HttpSession;



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
    private OrderService orderService;


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BaseService baseService;

    @Autowired
    private ProductService productService;


    @GetMapping("/")
    public String mainPage(HttpSession session){
        logger.info("Main controller");
        logger.info("User in HttpSession: " + session.getAttribute("user"));
        if (session.getAttribute("user") == null) {
            if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
                logger.info("Initialize user object in HttpSession");
                String name = SecurityContextHolder.getContext().getAuthentication().getName();
                User user = userService.findByName(name);
                session.setAttribute("user", user);
               if (session.getAttribute("user")!=null){
                   logger.info("Set User in session: " + user.toString());
               }
            }
        }
        return "index";
    }

    @GetMapping("/constructor")
    public String constructor(Model model){

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

}
