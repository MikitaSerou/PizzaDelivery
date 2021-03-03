package org.study.PizzaDelivery.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.model.*;
import org.study.PizzaDelivery.repository.BasketRepository;

import java.util.*;

@Service
public class BasketService {

    private static final Logger logger = LogManager.getLogger(BasketService.class);

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private BasketItemService basketItemService;

    @Autowired
    private ProductService productService;


    public void saveBasket(User user) {
        logger.info("Call method: saveBasket(user:" + user + ")");

        basketRepository.save(new Basket(true, user));
    }

    @Transactional
    public Basket findActiveByUserID(Long userId) {
        logger.info("Call method: findActiveByUserID(userId:" + userId + ")");
        Basket activeBasket = basketRepository.findByUserIdAndActiveIsTrue(userId);
        if (activeBasket != null) {
            logger.info(activeBasket);
        } else {
            logger.error("User with this id: " + userId + " has no active basket.");
        }

        return activeBasket;
    }

    @Transactional
    public Basket findById(Long basketId) {
        logger.info("Call method: findById(basketId:" + basketId + ")");
        Optional<Basket> basket = basketRepository.findById(basketId);
        basket.ifPresentOrElse(
                logger::info,
                () -> {
                    logger.error("Basket with this id: " + basketId + " is not exist.");
                });

        return basket.get();
    }

    public Double calculatePrice(Long basketId) {
        logger.info("Call method: calculatePrice(basketId:" + basketId + ")");
        List<BasketItem> items = basketItemService.getAllFromBasketByBasketId(basketId);
        Double sum = items.stream().mapToDouble(BasketItem::getPrice).sum();
        logger.info("Get sum of items: " + sum);

        return sum;
    }

    @Transactional
    public void addProductToBasket(User user, String productName, String comment, Short baseId) {
        logger.info("Call method: addProductToBasket(user:" + user +
                ", productName: " + productName +
                ", baseId: " + baseId +
                ", comment: " + comment + ")");
        Basket userBasket = findActiveByUserID(user.getId());
        Product product = productService.findByNameAndBaseId(productName, baseId);
        basketItemService.addItem(userBasket, product, comment);

        basketRepository.save(userBasket);
    }

    @Transactional
    public void addCustomProductToBasket(User user, Short baseId, Short sauceId, short[] ingredientsIds) {
        logger.info("Call method: addCustomProductToBasket(user:" + user +
                ", sauceId: " + sauceId +
                ", baseId: " + baseId +
                ", ingredientsIds: " + Arrays.toString(ingredientsIds) + ")");
        Basket userBasket = findActiveByUserID(user.getId());
        Product product = productService.findByNameAndBaseId("База", baseId);
        basketItemService.addCustomItem(userBasket, product, sauceId, ingredientsIds);

        basketRepository.save(userBasket);
    }

    public void clearBasket(Basket basket) {
        logger.info("Call method: clearBasket(basket:" + basket + ")");

        if (!basket.getBasketItems().isEmpty()) {
            User user = basket.getUser();
            basket.setActive(false);
            logger.info("Change status of basket: " + basket.getId() + "on active == false");
            basketRepository.save(basket);
            logger.info("Create new active basket for user: " + user.getId());
            basketRepository.save(new Basket(true, user));
        } else {
            logger.error("Basket: " + basket.getId() + "is already empty");
        }
    }
}
