package org.study.PizzaDelivery.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.data.model.Ingredient;

import java.util.Optional;
@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Short> {
    @Override
    <S extends Ingredient> S save(S s);

    @Override
    <S extends Ingredient> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    Optional<Ingredient> findById(Short aShort);

    @Override
    boolean existsById(Short aShort);

    @Override
    Iterable<Ingredient> findAll();

    @Override
    Iterable<Ingredient> findAllById(Iterable<Short> iterable);

    @Override
    long count();

    @Override
    void deleteById(Short aShort);

    @Override
    void delete(Ingredient ingredient);

    @Override
    void deleteAll(Iterable<? extends Ingredient> iterable);

    @Override
    void deleteAll();
}
