package org.study.PizzaDelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.study.PizzaDelivery.data.enums.IngredientType;
import org.study.PizzaDelivery.data.model.Base;
import org.study.PizzaDelivery.data.model.Category;
import org.study.PizzaDelivery.data.model.User;
import org.study.PizzaDelivery.data.service.*;

import java.util.List;


@Controller
@RequestMapping("/category")
@SessionAttributes("user")
public class CategoryController {

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
        model.addAttribute("categories", categoryService.getAllStandardCategories());
        //TODO скрыть кастомную
        //model.addAttribute("bases", baseService.findAll());
        model.addAttribute("cheapestProducts", productService.findAllByBase(baseService.findCheapest()));
        return "category/categories";
    }

    @GetMapping(value = "/{categoryName}/{productName}")
    public String show(@PathVariable("categoryName") String categoryName,
                       @PathVariable("productName") String productName,
                       Model model) {
        model.addAttribute("category", categoryService.findByName(categoryName));
        model.addAttribute("product", productService.findDistinctTopByName(productName));
        model.addAttribute("bases", baseService.findAll());
        return "category/product";
    }

    @PostMapping(value = "/{categoryName}/{productName}")
    public String addProductToCart(@PathVariable("categoryName") String categoryName,
                                   @PathVariable("productName") String productName,
                                   @ModelAttribute User user,
                                   @RequestParam(required = true, defaultValue = "") String comment,
                                   @RequestParam(required = true, defaultValue = "") Short baseId,
                                   Model model) {//TODO проверки на введенные null
        basketService.addProductToBasket(user, productName, comment, baseId);
        return "redirect:/category";
    }

    @GetMapping(value = "/{categoryName}/addProduct")
    public String addPizza(@PathVariable("categoryName") String categoryName,
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
        return "category/addProduct";
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
        if (action.equals("deleteProduct")) {
            productService.deleteAllVariablesOfProductByName(productName);
        }

        return "redirect:/category";
    }
}
