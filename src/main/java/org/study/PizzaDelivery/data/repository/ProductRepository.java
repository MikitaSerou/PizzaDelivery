package org.study.PizzaDelivery.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.data.model.Category;
import org.study.PizzaDelivery.data.model.Product;

import java.util.List;


@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Product findPizzaByCategory(Category category);

    List<Product> findAllByCategoryName(String categoryName);

    Product findById(long id);

    List<Product> findAllById(long id);

    List<Product> findAllByCategoryId(short id);
}
