package org.study.PizzaDelivery.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.study.PizzaDelivery.data.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Short> {


}
