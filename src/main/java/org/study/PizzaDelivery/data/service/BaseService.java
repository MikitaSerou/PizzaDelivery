package org.study.PizzaDelivery.data.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.study.PizzaDelivery.data.model.Base;
import org.study.PizzaDelivery.data.repository.BaseRepository;
@Service
public class BaseService {

    @Autowired
    BaseRepository baseRepository;

    public Base findOne(short id){
        return baseRepository.findById(id);
    }
}
