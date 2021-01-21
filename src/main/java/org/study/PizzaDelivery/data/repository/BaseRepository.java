package org.study.PizzaDelivery.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.data.model.Base;
@Repository
public interface BaseRepository extends CrudRepository<Base, Short> {


    Base findById(short id);
}
