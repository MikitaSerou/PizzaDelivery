package org.study.PizzaDelivery.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.model.Base;
import org.study.PizzaDelivery.model.Product;

import java.util.List;


@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Product findById(long id);

    List<Product> findAllByCategoryId(short categoryId);

    Product findDistinctTopByName(String name);

    boolean existsByName(String name);

    Product findByNameAndBaseId(String name, Short baseId);

    List<Product> findAllByName(String name);

    List<Product> findAllByBase(Base base);
}
