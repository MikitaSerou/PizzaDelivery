package org.study.PizzaDelivery.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.model.BasketItem;

import java.util.List;

@Repository
public interface BasketItemRepository extends CrudRepository<BasketItem, Long> {

    @Modifying
    @Query(value = "DELETE from BASKET_ITEM where ID = ?1", nativeQuery = true)
    void deleteByItemId(Long id);

    List<BasketItem> findAllByBasketId(Long basketId);
}
