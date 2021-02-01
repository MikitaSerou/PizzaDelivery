package org.study.PizzaDelivery.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.PizzaDelivery.data.enums.Type;
import org.study.PizzaDelivery.data.model.Ingredient;
import org.study.PizzaDelivery.data.repository.IngredientRepository;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    public Ingredient findById(Short ingredientId){
        return ingredientRepository.findById(ingredientId).get();
    }

    public void addIngredient(String ingredientName, Double ingredientPrice, Type ingredientType){
        Ingredient ingredientForAdd = new Ingredient(ingredientName, ingredientPrice, ingredientType);
        ingredientRepository.save(ingredientForAdd);
    }

    public void updateIngredient(Short ingredientId, String ingredientName, Double ingredientPrice,
                                 Type ingredientType){
        Ingredient ingredientForUpdate = ingredientRepository.findById(ingredientId).get();

        if(ingredientName!=null){ingredientForUpdate.setName(ingredientName);}

        if(ingredientPrice!=null){ingredientForUpdate.setPrice(ingredientPrice);}

        if(ingredientType!=null){ingredientForUpdate.setType(ingredientType);}

        ingredientRepository.save(ingredientForUpdate);
    }

    public void deleteIngredient(Short ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }

    public List<Ingredient> findAll(){
        return ingredientRepository.findAll();
    }
}
