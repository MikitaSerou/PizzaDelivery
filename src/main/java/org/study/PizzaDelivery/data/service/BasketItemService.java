package org.study.PizzaDelivery.data.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.controller.UserController;
import org.study.PizzaDelivery.data.model.Basket;
import org.study.PizzaDelivery.data.model.BasketItem;
import org.study.PizzaDelivery.data.model.Product;
import org.study.PizzaDelivery.data.model.User;
import org.study.PizzaDelivery.data.repository.BasketItemRepository;

import java.util.List;

@Service
public class BasketItemService {

    private static final Logger logger = LogManager.getLogger(BasketItemService.class);

    @Autowired
    private BasketItemRepository basketItemRepository;


    public List<BasketItem> getAllFromBasketByBasketId(Long basketId) {
        return basketItemRepository.findAllByBasketId(basketId);
    }


    @Transactional
    @Modifying
    public void addItem(Basket basket, Product product,  String description ){
        BasketItem item = new BasketItem(basket, product, product.getPrice(), description);
        System.err.println("addItem save item to basket: " + item.toString());
        basketItemRepository.save(item);

    }

    public void deleteItem(Long itemId){
        basketItemRepository.deleteByItemId(itemId);

    }



}
