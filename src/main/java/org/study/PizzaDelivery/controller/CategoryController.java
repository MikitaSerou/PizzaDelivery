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
        return "category/categories";
    }


    @GetMapping(value = "/{productId}")
    public String show(@PathVariable("productId") Long id, Model model) {
        model.addAttribute("product", productService.findOne(id));
        model.addAttribute("baseList", baseService.findAll());
        return "category/product";
    }


    @PostMapping
    public String ingredients(@RequestParam(required = true, defaultValue = "") Short categoryId,
                              @RequestParam(required = true, defaultValue = "") String categoryName,
                              @RequestParam(required = true, defaultValue = "") Double categoryPrice,
                              @RequestParam(required = true, defaultValue = "") String action,
                              Model model) {
//переписать
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
