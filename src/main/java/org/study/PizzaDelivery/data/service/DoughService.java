package org.study.PizzaDelivery.data.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.study.PizzaDelivery.data.model.Dough;
import org.study.PizzaDelivery.data.repository.DoughRepository;

public class DoughService {

    @Autowired
    DoughRepository doughRepository;

    public Dough findOne(short id){
        return doughRepository.findById(id);
    }
}
