package org.study.PizzaDelivery.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.enums.IngredientType;
import org.study.PizzaDelivery.model.Ingredient;
import org.study.PizzaDelivery.repository.IngredientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    private static final Logger logger = LogManager.getLogger(IngredientService.class);

    private final IngredientRepository ingredientRepository;


    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Transactional
    public Ingredient findById(Short ingredientId) {
        logger.info("Call method: findById(ingredientId: " + ingredientId + ")");
        Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
        ingredient.ifPresentOrElse(
                logger::info,
                () -> logger.error("Ingredient with this id: " + ingredientId + " is not exist."));

        return ingredientRepository.findById(ingredientId).orElse(null);
    }

    @Transactional
    public List<Ingredient> findByType(IngredientType ingredientType) {
        logger.info("Call method: findByType(ingredientType: " + ingredientType + ")");
        String type = ingredientType.toString().toUpperCase();

        return ingredientRepository.findAllByType(type);
    }

    public void addIngredient(String ingredientName, Double ingredientPrice, IngredientType ingredientType) {
        logger.info("Call method: addIngredient(ingredientName: " + ingredientName +
                ", ingredientPrice: " + ingredientPrice +
                ", ingredientType: " + ingredientType + ")");

        ingredientRepository.save(new Ingredient(ingredientName, ingredientPrice, ingredientType));
    }

    public void updateIngredient(Short ingredientId, String ingredientName, Double ingredientPrice,
                                 IngredientType ingredientType) {
        logger.info("Call method: updateIngredient(ingredientId: " + ingredientId +
                ", ingredientName: " + ingredientName +
                ", ingredientPrice: " + ingredientPrice +
                ", ingredientType: " + ingredientType + ")");
        Optional<Ingredient> ingredientForUpdate = ingredientRepository.findById(ingredientId);

        ingredientForUpdate.ifPresentOrElse(
                i -> {
                    logger.info(ingredientForUpdate);
                    if (ingredientName != null) {
                        i.setName(ingredientName);
                    }
                    if (ingredientPrice != null) {
                        i.setPrice(ingredientPrice);
                    }
                    if (ingredientType != null) {
                        i.setIngredientType(ingredientType);
                    }
                    ingredientRepository.save(i);
                },
                () -> logger.error("Ingredient with this id: " + ingredientId + " is not exist."));
    }

    public void deleteIngredient(Short ingredientId) {
        logger.info("Call method: deleteIngredient(ingredientId: " + ingredientId + ")");

        ingredientRepository.deleteById(ingredientId);
    }

    @Transactional
    public List<Ingredient> findAll() {
        logger.info("Call method: findAll()");

        return ingredientRepository.findAllAndOrderByIngredientType();
    }
}
