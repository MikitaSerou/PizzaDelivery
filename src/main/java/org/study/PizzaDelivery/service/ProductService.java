package org.study.PizzaDelivery.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.model.Base;
import org.study.PizzaDelivery.model.Category;
import org.study.PizzaDelivery.model.Ingredient;
import org.study.PizzaDelivery.model.Product;
import org.study.PizzaDelivery.repository.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.*;

@Service
public class ProductService {

    private static final Logger logger = LogManager.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BaseService baseService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private IngredientService ingredientService;

    @PersistenceContext
    EntityManager entityManager;


    @Transactional
    public Product findDistinctTopByName(String productName) {
        logger.info("Call method: findDistinctTopByName(productName: " + productName + ")");

        return productRepository.findDistinctTopByName(productName);
    }

    @Transactional
    public Product findByNameAndBaseId(String productName, Short baseId) {
        logger.info("Call method: findByNameAndBaseId(productName: " + productName +
                " baseId: " + baseId + ")");

        return productRepository.findByNameAndBaseId(productName, baseId);
    }

    @Transactional
    public void addNewProductToCategory(String name, Category category, String description, short[] ingredientsId) {
        logger.info("Call method: addNewProductToCategory(name:" + name +
                ", category:" + category +
                ", description:" + description +
                ", ingredientsId:" + Arrays.toString(ingredientsId) + ")");


        if (!name.equals("") && category != null) {

            List<Ingredient> ingredients = new ArrayList<>();
            for (short ingredientId : ingredientsId) {
                logger.info("Find ingredient by id: " + ingredientId);
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
                logger.info("Save product: " + product);
            });

        } else {
            logger.error("Name or Category of new product can not be null!");
        }
    }

    @Transactional
    public void editProductFromCategory(String name, String newName, String description, short[] ingredientsId) {
        logger.info("Call method: editProductFromCategory(name:" + name +
                ", newName:" + newName +
                ", description:" + description +
                ", ingredientsId:" + Arrays.toString(ingredientsId) + ")");

        List<Product> products = productRepository.findAllByName(name);
        if (!newName.equals("")) {

            for (Product product : products) {
                logger.info("Edit product with id: " + product.getId());
                product.setName(newName);

                List<Ingredient> ingredients = new ArrayList<>();
                for (short ingredientId : ingredientsId) {
                    logger.info("Find ingredient by id: " + ingredientId);
                    ingredients.add(ingredientService.findById(ingredientId));
                }

                if (!description.equals("")) {
                    product.setDescription(description);
                }

                product.setIngredients(ingredients);
                productRepository.save(product);
                logger.info("Update product: " + product);
            }
        } else {
            logger.error("Name of new product can not be empty");
        }
    }

    @Transactional
    public Iterable<Product> findAll() {
        logger.info("Call method: findAll()");

        return productRepository.findAll();
    }

    @Transactional
    public List<Product> findAllByCategoryId(Short categoryId) {
        logger.info("Call method: findAllByCategoryId(categoryId: " + categoryId + ")");

        return productRepository.findAllByCategoryId(categoryId);
    }

    @Transactional
    public List<Product> findAllByBase(Base base) {
        logger.info("Call method: findAllByBase(base: " + base + ")");

        return productRepository.findAllByBase(base);
    }

    @Transactional
    public void archiveProduct(Product product) {
        logger.info("Call method: archiveProduct(product: " + product + ")");

        product.setCategory(categoryService.findByName("Архив"));
        logger.info("Add the product: " + product + " to the archive category");
        productRepository.save(product);
    }

    @Transactional
    public void archiveAllVariablesOfProductByName(String productName) {
        logger.info("Call method: archiveAllVariablesOfProductByName(categoryId: " + productName + ")");

        if (productRepository.existsByName(productName)) {

            List<Product> products = productRepository.findAllByName(productName);
            for (Product product : products) {
                archiveProduct(product);
            }
        }
    }

    public Map<String, Product> findTop3Products() {
        logger.info("Call method: selectTop3Products()");
        Query query = entityManager.createNativeQuery("SELECT PRODUCT_id FROM (Select * From ORDER_ITEM " +
                "WHERE PRODUCT_ID in (select id from PRODUCT  WHERE PRODUCT.CATEGORY_ID > '2')) by " +
                "GROUP BY PRODUCT_id order by count(PRODUCT_id) desc");

        List<BigInteger> topId =  query.getResultList();


        Map<String, Product> topProducts = new HashMap<>();
        for (BigInteger id : topId) {
            Product product = productRepository.findById(id.longValue());
            if (!topProducts.containsKey(product.getName()) && topProducts.size() != 3) {
                topProducts.put(product.getName(), product);
            }
        }

        return topProducts;
    }
}
