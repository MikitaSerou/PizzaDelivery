package org.study.PizzaDelivery.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.study.PizzaDelivery.data.enums.IngredientType;
import org.study.PizzaDelivery.data.model.Category;
import org.study.PizzaDelivery.data.model.Product;
import org.study.PizzaDelivery.data.service.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LogManager.getLogger(AdminController.class);

    @Autowired
    private UserService userService;

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

    @GetMapping("/archive")
    public String showUser(Model model) {
        Category archiveCategory = categoryService.findByName("Архив");//TODO поменять потом на англ
        model.addAttribute("category", categoryService.findOne(archiveCategory.getId()));
        model.addAttribute("products", archiveCategory.getProducts());

        return "admin/archive";
    }


    @GetMapping(value = "/{categoryName}/addProduct")
    public String addProduct(@PathVariable("categoryName") String categoryName,
                             Model model) {
        model.addAttribute("category", categoryService.findByName(categoryName));
        model.addAttribute("bases", baseService.findAll());
        model.addAttribute("ingredients", ingredientService.findAll());
        model.addAttribute("sauces", ingredientService.findByType(IngredientType.SAUCE));
        model.addAttribute("cheeses", ingredientService.findByType(IngredientType.CHEESE));
        model.addAttribute("meat", ingredientService.findByType(IngredientType.MEAT));
        model.addAttribute("seafood", ingredientService.findByType(IngredientType.SEAFOOD));
        model.addAttribute("vegetables", ingredientService.findByType(IngredientType.VEGETABLE));
        model.addAttribute("ingredientTypes", IngredientType.values());
        return "admin/addProduct";
    }

    @PostMapping(value = "/{categoryName}/addProduct")
    public String addProductPage(@PathVariable("categoryName") String categoryName,
                                 @RequestParam(required = true, defaultValue = "") String productName,
                                 @RequestParam(required = true, defaultValue = "") String description,
                                 @RequestParam(required = true, defaultValue = "") short[] ingredientsIds,
                                 Model model) {

        productService.addNewProductToCategory(productName, categoryService.findByName(categoryName), description, ingredientsIds);

        return "redirect:/category";
    }


    @GetMapping("/edit/{productName}")
    public String showUser(@PathVariable("productName") String productName, Model model) {
        Product product = productService.findDistinctTopByName(productName);
        System.err.println(product.getIngredients());
        model.addAttribute("product", productService.findDistinctTopByName(productName));
       // model.addAttribute("productIngredients", product.getIngredients());
        model.addAttribute("bases", baseService.findAll());
        model.addAttribute("ingredients", ingredientService.findAll());
        model.addAttribute("sauces", ingredientService.findByType(IngredientType.SAUCE));
        model.addAttribute("cheeses", ingredientService.findByType(IngredientType.CHEESE));
        model.addAttribute("meat", ingredientService.findByType(IngredientType.MEAT));
        model.addAttribute("seafood", ingredientService.findByType(IngredientType.SEAFOOD));
        model.addAttribute("vegetables", ingredientService.findByType(IngredientType.VEGETABLE));
        model.addAttribute("ingredientTypes", IngredientType.values());
        return "admin/editProduct";
    }

    @PostMapping(value = "/edit/{productName}")
    public String editProductPage(@PathVariable("productName") String productName,
                                 @RequestParam(defaultValue = "") String newName,
                                 @RequestParam(defaultValue = "") String description,
                                 @RequestParam(defaultValue = "") short[] ingredientsIds,
                                 Model model) {
        productService.editProductFromCategory(productName, newName, description, ingredientsIds);

        return "redirect:/category";
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
        model.addAttribute("types", IngredientType.values());
        return "admin/ingredients";
    }

    @PostMapping("/ingredients")
    public String ingredients(@RequestParam(required = true, defaultValue = "") Short ingredientId,
                              @RequestParam(required = true, defaultValue = "") String ingredientName,
                              @RequestParam(required = true, defaultValue = "") Double ingredientPrice,
                              @RequestParam(required = true, defaultValue = "") IngredientType ingredientType,
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

