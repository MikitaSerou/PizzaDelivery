package org.study.PizzaDelivery.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.data.model.Category;
import org.study.PizzaDelivery.data.model.Product;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Product findPizzaByCategory(Category category);

    List<Product> findAllByCategoryName(String categoryName);

    @Override
    <S extends Product> S save(S s);

    @Override
    <S extends Product> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    Optional<Product> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    Iterable<Product> findAll();


    List<Product> findAllById(long id);

    List<Product> findAllByCategoryId(short id);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Product product);

    @Override
    void deleteAll(Iterable<? extends Product> iterable);

    @Override
    void deleteAll();

    @Override
    Iterable<Product> findAll(Sort sort);

    @Override
    Page<Product> findAll(Pageable pageable);
}
