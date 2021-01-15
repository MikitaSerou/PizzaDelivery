package org.study.PizzaDelivery.data.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.PizzaDelivery.data.model.Pizza;
import org.study.PizzaDelivery.data.repository.PizzaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;


    public Optional<Pizza> findOne(long id){
       return pizzaRepository.findById(id);
    }

    public List<Pizza> findByCategoryName(String name){ return pizzaRepository.findAllByCategoryName(name);}

    public Iterable<Pizza> findAll(){return pizzaRepository.findAll();}

    public List<Pizza> findAllById(long id){return pizzaRepository.findAllById(id);}

    public List<Pizza> findAllByCategoryId(short id){return pizzaRepository.findAllByCategoryId(id);}
}
