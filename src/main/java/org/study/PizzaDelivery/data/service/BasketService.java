package org.study.PizzaDelivery.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.data.model.Basket;
import org.study.PizzaDelivery.data.model.User;
import org.study.PizzaDelivery.data.repository.BasketRepository;
import org.study.PizzaDelivery.data.repository.UserRepository;

@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private UserRepository userRepository;

    public void createBasket(Long userId){
        //TODO уточнить, должны ли возвращать что-либо методы добавления в БД
        basketRepository.save(new Basket(userRepository.findById(userId).get()));
    }

    @Transactional
    public Basket findActiveByUserID(Long userId){
        //TODO переписать все методы с именем пользователя под id!
        return basketRepository.findByUserIdAndActiveIsTrue(userId);
    }

    public void clearBasket(Long basketId){
        Basket basketForClear = basketRepository.findById(basketId).get();
        User user = basketForClear.getUser();
        basketForClear.setActive(false);
        basketRepository.save(basketForClear);
        basketRepository.save(new Basket(true, user));
    }
}
