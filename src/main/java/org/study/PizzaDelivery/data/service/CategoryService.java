package org.study.PizzaDelivery.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.PizzaDelivery.data.model.Category;
import org.study.PizzaDelivery.data.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public Optional<Category> findOne(short id){
       return categoryRepository.findById(id);
    }

public List<Category> getAllCategoies(){
       return categoryRepository.findAll();
}
}
