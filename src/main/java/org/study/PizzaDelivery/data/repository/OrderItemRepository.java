package org.study.PizzaDelivery.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.data.model.OrderItem;

import java.util.Optional;
@Repository
public interface OrderItemRepository extends PagingAndSortingRepository<OrderItem, Long> {

}
