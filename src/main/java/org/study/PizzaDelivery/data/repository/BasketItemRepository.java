package org.study.PizzaDelivery.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.data.model.BasketItem;

@Repository
public interface BasketItemRepository extends CrudRepository<BasketItem, Long> {
}
