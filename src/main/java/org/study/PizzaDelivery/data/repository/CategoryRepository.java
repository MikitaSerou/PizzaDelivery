package org.study.PizzaDelivery.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.data.model.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Short> {

    Category findById(short id);

    Category findByName(String name);

    List<Category> findAll();
}
