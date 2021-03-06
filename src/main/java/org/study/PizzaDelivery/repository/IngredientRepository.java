package org.study.PizzaDelivery.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.model.Ingredient;

import java.util.List;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Short> {

    @Query(value = "SELECT * FROM INGREDIENT WHERE INGREDIENT_TYPE=?1",
            nativeQuery = true)
    List<Ingredient> findAllByType(String type);

    @Query(value = "SELECT * FROM INGREDIENT ORDER BY INGREDIENT_TYPE",
            nativeQuery = true)
    List<Ingredient> findAllAndOrderByIngredientType();
}
