package org.study.PizzaDelivery.data.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.controller.UserController;
import org.study.PizzaDelivery.data.model.*;
import org.study.PizzaDelivery.data.repository.BasketItemRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketItemService {

    private static final Logger logger = LogManager.getLogger(BasketItemService.class);

    @Autowired
    private BasketItemRepository basketItemRepository;

    @Autowired
    private IngredientService ingredientService;


    public List<BasketItem> getAllFromBasketByBasketId(Long basketId) {
        return basketItemRepository.findAllByBasketId(basketId);
    }


    @Transactional
    public void addItem(Basket basket, Product product, String description) {
        BasketItem item = new BasketItem(basket, product, product.getPrice(), description);
        basketItemRepository.save(item);
    }

    @Transactional
    public void addCustomItem(Basket basket, Product product, Short sauceId, short[] ingredientsIds) {
        StringBuilder ingredientsDescription = new StringBuilder("Ингридиенты: ");
        double customProductPrice = product.getCategory().getPrice() * product.getBase().getPriceMultiplier();

        if (sauceId != (short) 0) {
            ingredientsDescription.append(ingredientService.findById(sauceId).getName());
            customProductPrice += ingredientService.findById(sauceId).getPrice();
        }

        for (short ingredientId : ingredientsIds) {
            ingredientsDescription.append(", " + ingredientService.findById(ingredientId).getName());
            customProductPrice += ingredientService.findById(ingredientId).getPrice();
        }

        ingredientsDescription.append(".");
        BasketItem item = new BasketItem(basket, product, customProductPrice, ingredientsDescription.toString());
        basketItemRepository.save(item);
    }

    public void deleteItem(Long itemId) {
        basketItemRepository.deleteByItemId(itemId);

    }


}
