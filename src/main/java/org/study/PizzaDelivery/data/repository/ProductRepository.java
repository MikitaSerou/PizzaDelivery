package org.study.PizzaDelivery.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.data.model.Category;
import org.study.PizzaDelivery.data.model.Product;

import java.util.List;


@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Product findPizzaByCategory(Category category);

    List<Product> findAllByCategoryName(String categoryName);

    Product findById(long id);

    List<Product> findAllById(long id);

    List<Product> findAllByCategoryId(short categoryId);

    Product findDistinctTopByName(String name);



  //  @Modifying
    @Transactional
    @Query(value = "SELECT DISTINCT PRODUCT.name from PUBLIC.PRODUCT", nativeQuery = true)
    List<String> findDistinctNameByCategoryId();
}
