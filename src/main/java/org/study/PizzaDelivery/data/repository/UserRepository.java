package org.study.PizzaDelivery.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.study.PizzaDelivery.data.model.User;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    @Override
    <S extends User> S save(S s);

    @Override
    <S extends User> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    Optional<User> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    Iterable<User> findAll();

    @Override
    Iterable<User> findAllById(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(User user);

    @Override
    void deleteAll(Iterable<? extends User> iterable);

    @Override
    void deleteAll();

    @Override
    Iterable<User> findAll(Sort sort);

    @Override
    Page<User> findAll(Pageable pageable);
}
