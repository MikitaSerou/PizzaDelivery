package org.study.PizzaDelivery.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.data.model.Basket;

import java.util.List;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Long> {

    @Query(value = "SELECT * FROM BASKET WHERE IS_ACTIVE='true' AND USER_ID= ?1 ORDER BY ID DESC LIMIT 1",
            nativeQuery = true)
    Basket findByUserIdAndActiveIsTrue(long userId);

}
