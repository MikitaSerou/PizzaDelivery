package org.study.PizzaDelivery.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.study.PizzaDelivery.enums.IngredientType;
import org.study.PizzaDelivery.model.Category;
import org.study.PizzaDelivery.service.*;

import java.util.Arrays;

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
    public String cabinet(Model model) {
        logger.info("GET request /admin");
        model.addAttribute("countOfOrders", orderService.getCount());
        model.addAttribute("activeOrders", orderService.findNotPaidOrders().size());
        return "admin/adminOffice";
    }

    @GetMapping("/users")
    public String userList(Model model) {
        logger.info("GET request /users");

        model.addAttribute("allUsers", userService.findAll());

        return "admin/users";
    }

    @PostMapping
    public String deleteUser(@RequestParam(defaultValue = "") Long userId,
                             @RequestParam(defaultValue = "") String action) {
        logger.info("POST request /admin " +
                "[userId: " + userId + "]");

        userService.deleteUser(userId);

        return "redirect:/admin/users";
    }


    @GetMapping("/users/{userId}")
    public String showUser(@PathVariable("userId") Long userId, Model model) {
        logger.info("GET request admin/users/" + userId);

        model.addAttribute("user", userService.findUserById(userId));
        model.addAttribute("userOrders", orderService.findOrdersByUserId(userId));

        return "admin/userOrders";
    }

    @PostMapping("/users/{userId}")
    public String changeOrderStatus(@PathVariable("userId") Long userId,
                                    @RequestParam(defaultValue = "") Long orderId,
                                    @RequestParam(defaultValue = "") String action,
                                    Model model) {
        logger.info("POST request admin/users/" + userId +
                "[userId: " + userId +
                ", orderId: " + orderId +
                ", action: " + action + "]");

        if (action.equals("cancel")) {
            orderService.cancelOrder(orderId);
        }
        if (action.equals("paidUp")) {
            orderService.paidUpOrder(orderId);
        }
        if (action.equals("notPaid")) {
            orderService.setOrderNotPaidStatus(orderId);
        }

        return "redirect:/admin/users/" + userId;
    }

    @GetMapping("/archive")
    public String archive(Model model) {
        logger.info("GET request admin/archive/");

        Category archiveCategory = categoryService.findByName("Архив");
        model.addAttribute("category", categoryService.findOne(archiveCategory.getId()));
        model.addAttribute("products", archiveCategory.getProducts());

        return "admin/archive";
    }


    @GetMapping(value = "/{categoryName}/addProduct")
    public String addProduct(@PathVariable("categoryName") String categoryName,
                             Model model) {
        logger.info("GET request admin/" + categoryName + "/addProduct");

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
                                 @RequestParam(defaultValue = "") String productName,
                                 @RequestParam(defaultValue = "") String description,
                                 @RequestParam(defaultValue = "") short[] ingredientsIds,
                                 Model model) {
        logger.info("POST request admin/" + categoryName + "/addProduct " +
                "[categoryName: " + categoryName +
                ", productName: " + productName +
                ", description: " + description +
                ", ingredientsIds: " + Arrays.toString(ingredientsIds) + "]");

        if (productName.equals("")) {
            logger.error("Empty product name");
            model.addAttribute("addProductError", "add.product.error");
           return "redirect:/admin/" + categoryName + "/addProduct";

        }

        productService.addNewProductToCategory(productName, categoryService.findByName(categoryName), description, ingredientsIds);

        return "redirect:/category";
    }


    @GetMapping("/edit/{productName}")
    public String editProductPage(@PathVariable("productName") String productName, Model model) {
        logger.info("GET request admin/edit/" + productName);

        model.addAttribute("product", productService.findDistinctTopByName(productName));
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
    public String editProduct(@PathVariable("productName") String productName,
                              @RequestParam(defaultValue = "") String newName,
                              @RequestParam(defaultValue = "") String description,
                              @RequestParam(defaultValue = "") short[] ingredientsIds,
                              Model model) {
        logger.info("POST request admin/edit/" + productName +
                "[productName: " + productName +
                ", newName: " + newName +
                ", description: " + description +
                ", ingredientsIds: " + Arrays.toString(ingredientsIds) + "]");

        productService.editProductFromCategory(productName, newName, description, ingredientsIds);

        return "redirect:/category";
    }


    @GetMapping("/orders")
    public String activeOrdersList(Model model) {
        logger.info("GET request admin/orders");

        model.addAttribute("activeOrders", orderService.findNotPaidOrders());

        return "admin/orders";
    }

    @PostMapping("/orders")
    public String changeOrderStatus(@RequestParam(defaultValue = "") Long orderId,
                                    @RequestParam(defaultValue = "") String action,
                                    Model model) {
        logger.info("POST request admin/orders/" +
                "[orderId: " + orderId +
                ", action: " + action + "]");

        if (action.equals("cancel")) {
            orderService.cancelOrder(orderId);
        }
        if (action.equals("paidUp")) {
            orderService.paidUpOrder(orderId);
        }
        if (action.equals("notPaid")) {
            orderService.setOrderNotPaidStatus(orderId);
        }

        return "redirect:/admin/orders";
    }

    @GetMapping("/ingredients")
    public String ingredientsList(Model model) {
        logger.info("GET request admin/ingredients");

        model.addAttribute("ingredients", ingredientService.findAll());
        model.addAttribute("types", IngredientType.values());

        return "admin/ingredients";
    }

    @PostMapping("/ingredients")
    public String ingredients(@RequestParam(defaultValue = "") Short ingredientId,
                              @RequestParam(defaultValue = "") String ingredientName,
                              @RequestParam(defaultValue = "") Double ingredientPrice,
                              @RequestParam(defaultValue = "") IngredientType ingredientType,
                              @RequestParam(defaultValue = "") String action,
                              Model model) {
        logger.info("POST request admin/ingredients/" +
                "[ingredientId: " + ingredientId +
                ", ingredientName: " + ingredientName +
                ", ingredientPrice: " + ingredientPrice +
                ", ingredientType: " + ingredientType +
                ", action: " + action + "]");

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

