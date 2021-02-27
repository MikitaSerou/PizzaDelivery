package org.study.PizzaDelivery.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.data.enums.IngredientType;
import org.study.PizzaDelivery.data.model.Ingredient;

import java.util.List;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Short> {

    @Transactional
    List<Ingredient> findAll();

    @Transactional
    @Query(value = "SELECT * FROM INGREDIENT WHERE INGREDIENT_TYPE=?1",
            nativeQuery = true)
    List<Ingredient> findAllByType(String type);


    @Transactional
    @Query(value = "SELECT * FROM INGREDIENT ORDER BY INGREDIENT_TYPE",
            nativeQuery = true)
    List<Ingredient> findAllAndOrderByIngredientType();
}
