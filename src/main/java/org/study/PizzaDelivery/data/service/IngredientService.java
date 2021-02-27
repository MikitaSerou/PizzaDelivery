package org.study.PizzaDelivery.data.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.controller.UserController;
import org.study.PizzaDelivery.data.enums.IngredientType;
import org.study.PizzaDelivery.data.model.Ingredient;
import org.study.PizzaDelivery.data.repository.IngredientRepository;

import java.util.List;

@Service
public class IngredientService {

    private static final Logger logger = LogManager.getLogger(IngredientService.class);

    @Autowired
    private IngredientRepository ingredientRepository;

    public Ingredient findById(Short ingredientId){
        logger.debug("Input parameter: " + ingredientId);
        return ingredientRepository.findById(ingredientId).get();
    }

   public List<Ingredient> findByType(IngredientType ingredientType){
        String type = ingredientType.toString().toUpperCase();
        return ingredientRepository.findAllByType(type);
   }

    public void addIngredient(String ingredientName, Double ingredientPrice, IngredientType ingredientType){
        Ingredient ingredientForAdd = new Ingredient(ingredientName, ingredientPrice, ingredientType);
        ingredientRepository.save(ingredientForAdd);
    }

    public void updateIngredient(Short ingredientId, String ingredientName, Double ingredientPrice,
                                 IngredientType ingredientType){
        Ingredient ingredientForUpdate = ingredientRepository.findById(ingredientId).get();

        if(ingredientName!=null){ingredientForUpdate.setName(ingredientName);}

        if(ingredientPrice!=null){ingredientForUpdate.setPrice(ingredientPrice);}

        if(ingredientType!=null){ingredientForUpdate.setType(ingredientType);}

        ingredientRepository.save(ingredientForUpdate);
    }

    public void deleteIngredient(Short ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }

    @Transactional
    public List<Ingredient> findAll(){
        return ingredientRepository.findAllAndOrderByIngredientType();
    }


}
