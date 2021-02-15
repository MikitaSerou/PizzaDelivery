package org.study.PizzaDelivery.data.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.data.model.Base;
import org.study.PizzaDelivery.data.model.Category;
import org.study.PizzaDelivery.data.model.Ingredient;
import org.study.PizzaDelivery.data.model.Product;
import org.study.PizzaDelivery.data.repository.ProductRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BaseService baseService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private IngredientService ingredientService;


    public Product findOne(long id) {
        return productRepository.findById(id);
    }

    public Product findDistinctTopByName(String productName) {
        return productRepository.findDistinctTopByName(productName);
    }

    public void saveProduct(Product product){
        productRepository.save(product);
    }

    @Transactional
    public Product findByNameAndBaseId(String productName, Short baseId) {
        System.out.println(productName + " " + baseId);
        return productRepository.findByNameAndBaseId(productName, baseId);
    }

    public void deleteById(Long id) {
        Product product = productRepository.findById(id).get();
        for (Ingredient i : product.getIngredients()){
            ingredientService.deleteIngredient(i.getId());
        }
        productRepository.deleteById(id);
    }

/*    @Transactional
    @Modifying
    public void addNewProductWithoutIngredients(String name, Short categoryId, String description) {
        Category category = categoryService.findOne(categoryId);
        baseService.findAll().forEach(b -> {
            Product product = Product.builder()
                    .name(name)
                    .base(b)
                    .category(category)
                    .describe(description)
                    .price(category.getPrice() * b.getPriceMultiplier())
                    .build();
            productRepository.save(product);
        });*/

    /*        for (Base base:
                 baseService.findAll()) {
                Product product = Product.builder()
                        .name(name)
                        .base(base)
                        .category(category)
                        .describe(description)
                        .price(category.getPrice() * base.getPriceMultiplier())
                        .build();
                productRepository.save(product);
            }*/
    @Transactional
    @Modifying
    public void addNewProductToCategory(String name, Category category, Short sauceId, String description, short[] ingredientsId) {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

        for (short ingredientId : ingredientsId) {
            ingredients.add(ingredientService.findById(ingredientId));
        }

        baseService.findAll().forEach(b -> {
            Product product = Product.builder()
                    .name(name)
                    .base(b)
                    .category(category)
                    .describe(description)
                    .price(category.getPrice() * b.getPriceMultiplier())
                    .ingredients(ingredients)
                    .build();
            productRepository.save(product);
            System.err.println(product.toString());
        });
    }


    @Transactional
    public List<Product> findAllByBase(Base base) {
        return productRepository.findAllByBase(base);
    }

    public List<Product> findByCategoryName(String name) {
        return productRepository.findAllByCategoryName(name);
    }

    @Transactional
    public List<String> findAllDistinctNamesByCategoryId(Short categoryId) {
       return productRepository.findDistinctNamesByCategoryId(categoryId);
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findAllById(long id) {
        return productRepository.findAllById(id);
    }

    public List<Product> findAllByCategoryId(short id) {
        return productRepository.findAllByCategoryId(id);
    }

    @Transactional
    @Modifying
    public void deleteAllVariablesOfProductByName(String productName) {
        if (productRepository.existsByName(productName)) { //TODO вот так можно на null проверять
            List<Product> products = productRepository.findAllByName(productName);
            for (Product product : products) {
                product.setCategory(categoryService.findByName("Архив"));
                productRepository.save(product);
            }
        }
    }
}
