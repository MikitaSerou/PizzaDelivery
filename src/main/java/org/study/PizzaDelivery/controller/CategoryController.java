package org.study.PizzaDelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.PizzaDelivery.data.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String categoryList(Model model){
        model.addAttribute("categories", categoryService.getAllCategoies());
        return "category/categories.html";
    }
}
