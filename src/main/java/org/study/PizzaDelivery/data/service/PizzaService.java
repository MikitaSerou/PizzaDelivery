package org.study.PizzaDelivery.data.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.PizzaDelivery.data.model.Pizza;
import org.study.PizzaDelivery.data.repository.PizzaRepository;

import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;


    public Optional<Pizza> findOne(long id){
       return pizzaRepository.findById(id);
    }

}
