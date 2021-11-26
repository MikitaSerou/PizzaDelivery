package org.study.PizzaDelivery.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.model.Base;

@Repository
public interface BaseRepository extends CrudRepository<Base, Short> {

    @Query(value = "SELECT * FROM BASE ORDER BY PRICE_MULTIPLIER LIMIT 1", nativeQuery = true)
    Base findTopOrderByPriceAsc();
}
