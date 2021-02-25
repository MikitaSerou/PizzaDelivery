package org.study.PizzaDelivery.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.study.PizzaDelivery.data.enums.IngredientType;
import org.study.PizzaDelivery.data.model.Base;
import org.study.PizzaDelivery.data.model.Category;
import org.study.PizzaDelivery.data.model.Ingredient;
import org.study.PizzaDelivery.data.model.User;
import org.study.PizzaDelivery.data.service.*;

import java.util.ArrayList;
import java.util.List;


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
        model.addAttribute("customCategory", categoryService.findOne((short) 2));
        model.addAttribute("categories", categoryService.getAllStandardCategories());
        model.addAttribute("bases", baseService.findAll());
        model.addAttribute("cheapestProducts", productService.findAllByBase(baseService.findCheapest()));

        return "category/categories";
    }

    @GetMapping(value = "/{categoryName}/{productName}")
    public String show(@PathVariable("categoryName") String categoryName,
                       @PathVariable("productName") String productName,
                       Model model) {
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
                                   @RequestParam(defaultValue = "") Short baseId,
                                   @RequestParam(defaultValue = "") String action,
                                   Model model) {//TODO проверки на введенные null

        if (action.equals("addToBasket")) {
            logger.info("Add to basket request: " + productName + user.getId() + baseId);
            System.err.println("Add to basket request: " + productName + "userId: " +  user.getId()+ "base.id: " + baseId + "baseName: " + baseService.findById(baseId) + "comment: " + comment);
            basketService.addProductToBasket(user, productName, comment, baseId);
        }

        if(action.equals("edit")){
            logger.info("Edit request: " + categoryName + " " + productName + " " + comment+ " " + action);
        }

        return "redirect:/category";
    }

    @PostMapping
    public String ingredients(@RequestParam(required = true, defaultValue = "") Short categoryId,
                              @RequestParam(required = true, defaultValue = "") String categoryName,
                              @RequestParam(required = true, defaultValue = "") String productName,
                              @RequestParam(required = true, defaultValue = "") Double categoryPrice,
                              @RequestParam(required = true, defaultValue = "") String action,
                              Model model) {

        if (action.equals("delete")) {
            categoryService.deleteCategory(categoryId);
        }
        if (action.equals("add")) {
            categoryService.addCategory(categoryName, categoryPrice);
        }
        if (action.equals("edit")) {
            System.out.println("EDIT");
            categoryService.editCategory(categoryId, categoryName, categoryPrice);
        }
        if (action.equals("deleteProduct")) { //TODO не работает
            System.err.println(productName + "deleteAss");
            productService.deleteAllVariablesOfProductByName(productName);
        }

        return "redirect:/category";
    }
}
