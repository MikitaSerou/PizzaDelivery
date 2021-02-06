package org.study.PizzaDelivery.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.data.model.Basket;
import org.study.PizzaDelivery.data.model.BasketItem;
import org.study.PizzaDelivery.data.model.User;
import org.study.PizzaDelivery.data.repository.BasketRepository;
import org.study.PizzaDelivery.data.repository.UserRepository;

import java.util.*;
import java.util.stream.DoubleStream;

@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private UserService userService;

    @Autowired
    BasketItemService basketItemService;


    public void saveBasket(User user){
        basketRepository.save(new Basket(true, user));
    }

    public Basket getActiveBasketByUserId(Long userId){
        return basketRepository.findByUserIdAndActiveIsTrue(userId);
    }

    //TODO написать проверки на налл в подобных
    public Basket findById(Long basketId){
       return basketRepository.findById(basketId).get();
    }

    public void createBasket(Long userId){
        basketRepository.save(new Basket(userService.findUserById(userId)));
    }


    public Double calculatePrice(Long basketId){
        List<BasketItem> items = basketItemService.getAllFromBasketByBasketId(basketId);
        return items.stream().mapToDouble(BasketItem::getPrice).sum();
    }

    @Transactional
    public Basket findActiveByUserID(Long userId){
        return basketRepository.findByUserIdAndActiveIsTrue(userId);
    }

    public void clearBasket(Long basketId){
        Basket basketForClear = basketRepository.findById(basketId).get();
        if (!basketForClear.getBasketItems().isEmpty()){
        User user = basketForClear.getUser();
        basketForClear.setActive(false);
        basketRepository.save(basketForClear);
        basketRepository.save(new Basket(true, user));
    }
        else{
            System.out.println("ALREADY empty EBAT");
        }
    }

}
