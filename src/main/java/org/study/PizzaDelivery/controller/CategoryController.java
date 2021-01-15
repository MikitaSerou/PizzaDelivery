package org.study.PizzaDelivery.controller;

//import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.PizzaDelivery.data.service.CategoryService;
import org.study.PizzaDelivery.data.service.PizzaService;

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


    @GetMapping(value = "/{categoryName}")
    public String show(@PathVariable("categoryName") String name, Model model) {
        model.addAttribute("pizzaList", pizzaService.findByCategoryName(name));
        model.addAttribute("categoryName", name);
        return "category/showPizzaByCategory";
    }
}
