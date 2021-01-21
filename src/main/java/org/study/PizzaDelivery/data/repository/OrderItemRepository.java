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
    @Override
    Iterable<OrderItem> findAll(Sort sort);

    @Override
    Page<OrderItem> findAll(Pageable pageable);

    @Override
    <S extends OrderItem> S save(S s);

    @Override
    <S extends OrderItem> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    Optional<OrderItem> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    Iterable<OrderItem> findAll();

    @Override
    Iterable<OrderItem> findAllById(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(OrderItem orderItem);

    @Override
    void deleteAll(Iterable<? extends OrderItem> iterable);

    @Override
    void deleteAll();
}
