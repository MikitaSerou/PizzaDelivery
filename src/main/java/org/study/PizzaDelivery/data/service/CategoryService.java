package org.study.PizzaDelivery.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.PizzaDelivery.data.model.Category;
import org.study.PizzaDelivery.data.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public Category findOne(short id) {
        return categoryRepository.findById(id);
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    public void addCategory(String categoryName, Double categoryPrice) {
        Category categoryForAdd = new Category(categoryName, categoryPrice);
        categoryRepository.save(categoryForAdd);
    }

    public void editCategory(Short categoryId, String categoryName, Double categoryPrice) {
        Category categoryForUpdate = categoryRepository.findById(categoryId).get();
        if (categoryName != null) {
            categoryForUpdate.setName(categoryName);
        }
        if (categoryPrice != null) {
            categoryForUpdate.setPrice(categoryPrice);
        }
        categoryRepository.save(categoryForUpdate);
    }

    public void deleteCategory(Short categoryId) {
        categoryRepository.delete(categoryRepository.findById(categoryId).get());
    }
}
