package org.study.PizzaDelivery.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.study.PizzaDelivery.data.model.Category;
import org.study.PizzaDelivery.data.model.Dough;

import java.util.Optional;

public interface DoughRepository extends CrudRepository<Dough, Short> {


    Dough findById(short id);
}
