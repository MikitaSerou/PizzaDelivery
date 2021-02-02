package org.study.PizzaDelivery.data.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.data.model.BasketItem;

@Repository
public interface BasketItemRepository extends CrudRepository<BasketItem, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE from BASKET_ITEM where ID = ?1", nativeQuery = true)
    void deleteByItemId(long id);
}
