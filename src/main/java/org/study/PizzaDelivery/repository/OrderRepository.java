package org.study.PizzaDelivery.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.enums.Status;
import org.study.PizzaDelivery.model.Category;
import org.study.PizzaDelivery.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

    List<Order> findAllByStatus(Status status);

    @Query(value = "SELECT * FROM ORDERS Where USER_ID = ?1  Order by TIME DESC",
            nativeQuery = true)
    @Transactional
    List<Order> findAllByUserId(long userId);

    Order findById(long aLong);

}
