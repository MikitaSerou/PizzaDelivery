package org.study.PizzaDelivery.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.study.PizzaDelivery.data.model.Dough;

import java.util.Optional;

public interface DoughRepository extends CrudRepository<Dough, Short> {
    @Override
    <S extends Dough> S save(S s);

    @Override
    <S extends Dough> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    Optional<Dough> findById(Short aShort);

    @Override
    boolean existsById(Short aShort);

    @Override
    Iterable<Dough> findAll();

    @Override
    Iterable<Dough> findAllById(Iterable<Short> iterable);

    @Override
    long count();

    @Override
    void deleteById(Short aShort);

    @Override
    void delete(Dough dough);

    @Override
    void deleteAll(Iterable<? extends Dough> iterable);

    @Override
    void deleteAll();
}
