package org.study.PizzaDelivery.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.model.Category;
import org.study.PizzaDelivery.model.Product;
import org.study.PizzaDelivery.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private static final Logger logger = LogManager.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;

    private final ProductService productService;


    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    @Transactional
    public Category findByName(String name) {
        logger.info("Call method: findByName(name: " + name + ")");

        return categoryRepository.findByName(name);
    }

    @Transactional
    public List<Category> getAllStandardCategories() {
        logger.info("Call method: getAllStandardCategories()");

        return categoryRepository.findAllStandard();
    }

    public void addCategory(String categoryName, Double categoryPrice) {
        logger.info("Call method: addCategory(categoryName:" + categoryName +
                ", categoryPrice:" + categoryPrice + ")");
        if (categoryName != null && categoryPrice != null) {
            Category categoryForAdd = new Category(categoryName, categoryPrice);
            categoryRepository.save(categoryForAdd);
        } else {
            logger.error("Both parameters must be not null!");
        }
    }

    public void editCategory(Short categoryId, String categoryName, Double categoryPrice) {
        logger.info("Call method: editCategory(categoryId:" + categoryId +
                ", categoryName:" + categoryName +
                ", categoryPrice:" + categoryPrice + ")");
        Optional<Category> categoryForUpdate = categoryRepository.findById(categoryId);

        categoryForUpdate.ifPresentOrElse(
                c -> {
                    logger.info(categoryForUpdate);
                    if (!categoryName.equals("")) {
                        c.setName(categoryName);
                    }
                    if (categoryPrice != null) {
                        c.setPrice(categoryPrice);
                    }
                    categoryRepository.save(c);
                },
                () -> logger.error("Category with this id: " + categoryId + " is not exist."));
    }

    @Transactional
    @Modifying
    public void deleteCategory(Short categoryId) {
        logger.info("Call method: deleteCategory(categoryId: " + categoryId + ")");

        productService.findAllByCategoryId(categoryId);
        for (Product product : productService.findAllByCategoryId(categoryId)) {
            productService.archiveProduct(product);
        }

        Optional<Category> categoryForDelete = categoryRepository.findById(categoryId);
        categoryForDelete.ifPresentOrElse(
                c -> {
                    logger.info(c);
                    categoryRepository.delete(c);
                },
                () -> logger.error("Category with this id: " + categoryId + " is not exist."));
    }
}
