package org.study.PizzaDelivery.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.model.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Short> {

    Category findById(short id);

    Category findByName(String name);

    List<Category> findAll();

    @Query(value = " SELECT * FROM CATEGORY WHERE NAME !='Своя' AND NAME !='Архив' Order by CATEGORY_PRICE",
            nativeQuery = true)
    List<Category> findAllStandard();
}
