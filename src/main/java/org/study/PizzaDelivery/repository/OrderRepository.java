package org.study.PizzaDelivery.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.enums.Status;
import org.study.PizzaDelivery.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

    List<Order> findAllByStatus(Status status);

    List<Order> findAllByUserUsername(String userName);

    List<Order> findAllByUserId(long userId);

    Order findById(long aLong);

}
