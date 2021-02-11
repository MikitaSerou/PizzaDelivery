package org.study.PizzaDelivery.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.data.model.Base;

import java.util.List;

@Repository
public interface BaseRepository extends CrudRepository<Base, Short> {

    Base findById(short id);

    @Transactional
    List<Base> findAll();

    @Query(value = "SELECT TOP 1 *FROM BASE ORDER BY PRICE_MULTIPLIER ASC", nativeQuery = true)
    Base findTopOrderByPriceAsc();


}
