package org.study.PizzaDelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    BaseService baseService;

    @Autowired
    BasketService basketService;

    @GetMapping
    public String categoryList(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        //model.addAttribute("bases", baseService.findAll());
        model.addAttribute("cheapestProducts", productService.findAllByBase(baseService.findCheapest()));
        return "category/categories";
    }


    @GetMapping(value = "/{productName}") //TODO сделать под нейм SELECT TOP 1 * FROM PRODUCT WHERE NAME = 'Majorino';
    public String show(@PathVariable("productName") String productName, Model model) {
        model.addAttribute("product", productService.findDistinctTopByName(productName));
        model.addAttribute("baseList", baseService.findAll());
        return "category/product";
    }

    @PostMapping(value = "/{productName}")
    public String addProductToCart(@PathVariable("productName") String productName,
                              @ModelAttribute User user,
                              @RequestParam(required = true, defaultValue = "") String comment,
                              @RequestParam(required = true, defaultValue = "") Short baseId,
                              Model model) {//TODO проверки на введенные null
basketService.addProductToBasket(user, productName, comment, baseId);

        System.out.println(user.getId() + " " + comment + " " + baseId);
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
        if (action.equals("deleteProduct")) {
            productService.deleteAllvariablesOfProductByName(productName);
        }

        return "redirect:/category";
    }
}
