package org.study.PizzaDelivery.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.model.Base;

import java.util.List;

@Repository
public interface BaseRepository extends CrudRepository<Base, Short> {

    Base findById(short id);

    List<Base> findAll();

    @Query(value = "SELECT TOP 1 * FROM BASE ORDER BY PRICE_MULTIPLIER", nativeQuery = true)
    Base findTopOrderByPriceAsc();
}
