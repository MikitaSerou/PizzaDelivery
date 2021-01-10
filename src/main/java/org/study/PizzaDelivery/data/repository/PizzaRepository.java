package org.study.PizzaDelivery.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.data.model.Category;
import org.study.PizzaDelivery.data.model.Pizza;


@Repository
public interface PizzaRepository extends PagingAndSortingRepository<Pizza, Long> {

    Pizza findPizzaByCategory(Category category);


}
