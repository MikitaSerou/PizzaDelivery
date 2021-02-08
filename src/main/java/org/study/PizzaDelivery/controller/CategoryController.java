package org.study.PizzaDelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.study.PizzaDelivery.data.model.Category;
import org.study.PizzaDelivery.data.service.BaseService;
import org.study.PizzaDelivery.data.service.CategoryService;
import org.study.PizzaDelivery.data.service.ProductService;

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

    @GetMapping
    public String categoryList(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("bases", baseService.findAll());
        model.addAttribute("firstBase", baseService.findById((short) 1));//TODO сделать метод для нахождения самой дешевой SELECT TOP 1 *FROM BASE ORDER BY price ASC;
        return "category/categories";
    }


    @GetMapping(value = "/{productName}") //TODO сделать под нейм SELECT TOP 1 * FROM PRODUCT WHERE NAME = 'Majorino';
    public String show(@PathVariable("productName") String productName, Model model) {
        model.addAttribute("product", productService.findDistinctTopByName(productName));
        model.addAttribute("baseList", baseService.findAll());
        return "category/product";
    }


    @PostMapping
    public String ingredients(@RequestParam(required = true, defaultValue = "") Short categoryId,
                              @RequestParam(required = true, defaultValue = "") String categoryName,
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

        return "redirect:/category";
    }
}
