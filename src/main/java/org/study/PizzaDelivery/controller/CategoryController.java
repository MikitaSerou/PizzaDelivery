package org.study.PizzaDelivery.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.study.PizzaDelivery.enums.IngredientType;
import org.study.PizzaDelivery.model.User;
import org.study.PizzaDelivery.service.*;

@Controller
@RequestMapping("/category")
@SessionAttributes("user")
public class CategoryController {

    private static final Logger logger = LogManager.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BaseService baseService;

    @Autowired
    private BasketService basketService;

    @Autowired
    private IngredientService ingredientService;


    @GetMapping
    public String categoryList(Model model) {
        logger.info("GET request /category");

        model.addAttribute("customCategory", categoryService.findByName("Своя"));
        model.addAttribute("categories", categoryService.getAllStandardCategories());
        model.addAttribute("bases", baseService.findAll());
        model.addAttribute("cheapestProducts", productService.findAllByBase(baseService.findCheapest()));
        model.addAttribute("top3Names", productService.findTop3Products().keySet());

        return "category/categories";
    }

    @GetMapping(value = "/{categoryName}/{productName}")
    public String show(@PathVariable("categoryName") String categoryName,
                       @PathVariable("productName") String productName,
                       Model model) {
        logger.info("GET request /category/" + categoryName + "/" + productName);

        model.addAttribute("category", categoryService.findByName(categoryName));
        model.addAttribute("product", productService.findDistinctTopByName(productName));
        model.addAttribute("ingredients", ingredientService.findAll());
        model.addAttribute("bases", baseService.findAll());
        model.addAttribute("cheeses", ingredientService.findByType(IngredientType.CHEESE));
        model.addAttribute("sauces", ingredientService.findByType(IngredientType.SAUCE));
        model.addAttribute("meat", ingredientService.findByType(IngredientType.MEAT));
        model.addAttribute("seafood", ingredientService.findByType(IngredientType.SEAFOOD));
        model.addAttribute("vegetables", ingredientService.findByType(IngredientType.VEGETABLE));
        model.addAttribute("ingredientTypes", IngredientType.values());

        return "category/product";
    }

    @PostMapping(value = "/{categoryName}/{productName}")
    public String addProductToCart(@PathVariable("categoryName") String categoryName,
                                   @PathVariable("productName") String productName,
                                   @ModelAttribute User user,
                                   @RequestParam(defaultValue = "") String comment,
                                   @RequestParam(defaultValue = "") Short baseId) {
        logger.info("POST request /category/" + categoryName + "/" + productName +
                "[categoryName: " + categoryName +
                ", productName: " + productName +
                ", user: " + user +
                ", comment: " + comment +
                ", baseId: " + baseId + "]");

        basketService.addProductToBasket(user, productName, comment, baseId);

        return "redirect:/category";
    }

    @PostMapping
    public String ingredients(@RequestParam(defaultValue = "") Short categoryId,
                              @RequestParam(defaultValue = "") String categoryName,
                              @RequestParam(defaultValue = "") String productName,
                              @RequestParam(defaultValue = "") Double categoryPrice,
                              @RequestParam(defaultValue = "") String action,
                              Model model) {
        logger.info("POST request /category/" +
                "[categoryId: " + categoryId +
                ", categoryName: " + categoryName +
                ", productName: " + productName +
                ", categoryPrice: " + categoryPrice +
                ", action: " + action + "]");

        if (action.equals("delete")) {
            categoryService.deleteCategory(categoryId);
        }
        if (action.equals("add")) {
            if (categoryName.equals("") || categoryPrice == null) {
                logger.error("Not all parameters are specified");
                model.addAttribute("categoryError", "add.category.error");
            } else {
                categoryService.addCategory(categoryName, categoryPrice);
            }
        }
        if (action.equals("edit")) {
            categoryService.editCategory(categoryId, categoryName, categoryPrice);
        }
        if (action.equals("deleteProduct")) {
            productService.archiveAllVariablesOfProductByName(productName);
        }

        return "redirect:/category";
    }
}
