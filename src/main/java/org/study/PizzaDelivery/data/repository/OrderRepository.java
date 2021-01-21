package org.study.PizzaDelivery.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.data.model.Order;

import java.util.Optional;
@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
    @Override
    Iterable<Order> findAll(Sort sort);

    @Override
    Page<Order> findAll(Pageable pageable);

    @Override
    <S extends Order> S save(S s);

    @Override
    <S extends Order> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    Optional<Order> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    Iterable<Order> findAll();

    @Override
    Iterable<Order> findAllById(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Order order);

    @Override
    void deleteAll(Iterable<? extends Order> iterable);

    @Override
    void deleteAll();
}
