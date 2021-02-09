package org.study.PizzaDelivery.data.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.data.model.Base;
import org.study.PizzaDelivery.data.repository.BaseRepository;

import java.util.List;

@Service
public class BaseService {

    @Autowired
    BaseRepository baseRepository;

    public Base findById(short id) {
        return baseRepository.findById(id);
    }

    @Transactional
    public List<Base> findAll() {
        return baseRepository.findAll();
    }

    public Base findCheapest(){
        return baseRepository.findTopOrderByPriceAsc();
    }
}
