package org.study.PizzaDelivery.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.data.enums.Status;
import org.study.PizzaDelivery.data.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

    List<Order> findAllByStatus(Status status);

    @Query(value = "SELECT * FROM ORDERS WHERE STATUS='NOT_PAID'",
    nativeQuery = true)
    List<Order> findAllByStatusNotPaid();

    List<Order> findAllByUserUsername(String userName);

    List<Order> findAllByUserId(long userId);

    Order findById(long aLong);

}
