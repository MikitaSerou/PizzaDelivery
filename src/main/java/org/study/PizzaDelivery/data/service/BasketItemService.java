package org.study.PizzaDelivery.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.PizzaDelivery.data.model.Basket;
import org.study.PizzaDelivery.data.model.BasketItem;
import org.study.PizzaDelivery.data.repository.BasketItemRepository;

import java.util.List;

@Service
public class BasketItemService {

    @Autowired
    private BasketItemRepository basketItemRepository;


    public List<BasketItem> getAllFromBasketByBasketId(Long basketId) {
        return basketItemRepository.findAllByBasketId(basketId);
    }

    public void deleteItem(Long itemId){
        basketItemRepository.deleteByItemId(itemId);
    //deleteById(itemId);
    }

}
