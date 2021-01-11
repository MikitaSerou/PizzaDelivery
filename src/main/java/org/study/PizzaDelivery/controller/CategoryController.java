package org.study.PizzaDelivery.controller;

//import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.study.PizzaDelivery.data.service.CategoryService;
import org.study.PizzaDelivery.data.service.PizzaService;

import java.awt.*;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    PizzaService pizzaService;

    @GetMapping
    public String categoryList(Model model){
        model.addAttribute("categories", categoryService.getAllCategoies());
        return "category/categories";
    }


    @GetMapping(value = "/pizzasByCategory{id}")
    public Model getPizzas(Model model,
                                 @RequestParam("id") Short id) {
        model.addAttribute("pizzas", pizzaService.findAllByCategoryId(id));
        model.addAttribute("categoryId", id);
        return model;
    }

}
