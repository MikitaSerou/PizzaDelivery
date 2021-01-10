package org.study.PizzaDelivery.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.study.PizzaDelivery.data.model.Item;

import java.util.Optional;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
    @Override
    Iterable<Item> findAll(Sort sort);

    @Override
    Page<Item> findAll(Pageable pageable);

    @Override
    <S extends Item> S save(S s);

    @Override
    <S extends Item> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    Optional<Item> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    Iterable<Item> findAll();

    @Override
    Iterable<Item> findAllById(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Item orderItem);

    @Override
    void deleteAll(Iterable<? extends Item> iterable);

    @Override
    void deleteAll();
}
