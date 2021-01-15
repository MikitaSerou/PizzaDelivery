package org.study.PizzaDelivery.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.study.PizzaDelivery.data.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Short> {

    Category findById(short id);

    Category findByName(String name);

    List<Category> findAll();
}
