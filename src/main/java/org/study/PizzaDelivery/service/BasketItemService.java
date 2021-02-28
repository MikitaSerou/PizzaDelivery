package org.study.PizzaDelivery.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.model.*;
import org.study.PizzaDelivery.repository.BasketItemRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class BasketItemService {

    private static final Logger logger = LogManager.getLogger(BasketItemService.class);

    @Autowired
    private BasketItemRepository basketItemRepository;

    @Autowired
    private IngredientService ingredientService;


    public List<BasketItem> getAllFromBasketByBasketId(Long basketId) {
        logger.info("Call method: getAllFromBasketByBasketId(basketId:" + basketId + ")");
        List<BasketItem> items = basketItemRepository.findAllByBasketId(basketId);
        if (!items.isEmpty()) {
            logger.info(items);
        } else {
            logger.error("Basket with this id: " + basketId + " is empty.");
        }

        return items;
    }

    @Transactional
    public void addItem(Basket basket, Product product, String description) {
        logger.info("Call method: addItem(basket:" + basket +
                ", product: " + product +
                ", description: " + description + ")");

        basketItemRepository.save(new BasketItem(basket, product, product.getPrice(), description));
    }

    @Transactional
    public void addCustomItem(Basket basket, Product product, Short sauceId, short[] ingredientsIds) {
        logger.info("Call method: addCustomItem(basket:" + basket +
                ", product: " + product +
                ", sauceId: " + sauceId +
                ", ingredientsIds: " + Arrays.toString(ingredientsIds) + ")");
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

        basketItemRepository.save(new BasketItem(basket, product, customProductPrice, ingredientsDescription.toString()));
    }

    public void deleteItem(Long itemId) {
        logger.info("Call method: deleteItem(itemId:" + itemId + ")");

        basketItemRepository.deleteByItemId(itemId);
    }


}
