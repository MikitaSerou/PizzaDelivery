package org.study.PizzaDelivery.data.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.controller.UserController;
import org.study.PizzaDelivery.data.model.Category;
import org.study.PizzaDelivery.data.model.Product;
import org.study.PizzaDelivery.data.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private static final Logger logger = LogManager.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductService productService;

    @Transactional
    public Category findOne(short id) {
        return categoryRepository.findById(id);
    }

    @Transactional
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Transactional
    public List<Category> getAllStandardCategories(){
        return categoryRepository.findAllStandard();
    }

    @Transactional
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }




    public void addCategory(String categoryName, Double categoryPrice) {
        if(categoryName != null && categoryPrice != null){
        Category categoryForAdd = new Category(categoryName, categoryPrice);
        categoryRepository.save(categoryForAdd);}
    }

    public void editCategory(Short categoryId, String categoryName, Double categoryPrice) {
        Category categoryForUpdate = categoryRepository.findById(categoryId).get();
        if (!categoryName.equals("")) {
            categoryForUpdate.setName(categoryName);
        }
        if (categoryPrice != null) {
            categoryForUpdate.setPrice(categoryPrice);
        }
        categoryRepository.save(categoryForUpdate);
    }

    @Transactional
    @Modifying
    public void deleteCategory(Short categoryId) {
        List<String> distinctNamesOfCategoryProducts = productService.findAllDistinctNamesByCategoryId(categoryId);
        //category.setProducts(new ArrayList<Product>());
        for (String n : distinctNamesOfCategoryProducts) {
            productService.deleteAllVariablesOfProductByName(n);
        }
       categoryRepository.delete(categoryRepository.findById(categoryId).get());
    /*     categoryRepository.delete(categoryRepository.findById(categoryId).get());*/
    }
}
