package org.study.PizzaDelivery.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.model.Base;
import org.study.PizzaDelivery.model.Product;

import java.util.List;


@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Product findById(long id);

    @Transactional
    List<Product> findAllByCategoryId(short categoryId);

    Product findDistinctTopByName(String name);

    boolean existsByName(String name);

    @Transactional
    Product findByNameAndBaseId(String name, Short baseId);

    @Transactional
    List<Product> findAllByName(String name);

    @Transactional
    List<Product> findAllByBase(Base base);
}
