package org.study.PizzaDelivery.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.data.model.Category;
import org.study.PizzaDelivery.data.model.Pizza;

import java.util.List;
import java.util.Optional;


@Repository
public interface PizzaRepository extends PagingAndSortingRepository<Pizza, Long> {

    Pizza findPizzaByCategory(Category category);

    @Override
    <S extends Pizza> S save(S s);

    @Override
    <S extends Pizza> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    Optional<Pizza> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    Iterable<Pizza> findAll();


    List<Pizza> findAllById(long id);

    List<Pizza> findAllByCategoryId(short id);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Pizza pizza);

    @Override
    void deleteAll(Iterable<? extends Pizza> iterable);

    @Override
    void deleteAll();

    @Override
    Iterable<Pizza> findAll(Sort sort);

    @Override
    Page<Pizza> findAll(Pageable pageable);
}
