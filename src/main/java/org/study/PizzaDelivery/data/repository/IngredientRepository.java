package org.study.PizzaDelivery.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.data.model.Ingredient;

import java.util.List;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Short> {

    List<Ingredient> findAll();

}
