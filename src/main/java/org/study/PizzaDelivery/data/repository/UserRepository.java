package org.study.PizzaDelivery.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.data.model.User;


import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByUsername(String username);

    List<User> findAll();

    @Override
    Optional<User> findById(Long aLong);
}
