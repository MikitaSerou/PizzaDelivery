package org.study.PizzaDelivery.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.PizzaDelivery.data.model.Basket;
import org.study.PizzaDelivery.data.repository.BasketItemRepository;

@Service
public class BasketItemService {

    @Autowired
    private BasketItemRepository basketItemRepository;


    public void deleteItem(Long itemId){
        basketItemRepository.deleteByItemId(itemId);
    //deleteById(itemId);
    }

}
