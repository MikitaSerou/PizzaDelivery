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
import org.study.PizzaDelivery.data.repository.BasketRepository;
import org.study.PizzaDelivery.data.repository.UserRepository;

import java.util.*;
import java.util.stream.DoubleStream;

@Service
public class BasketService {

    private static final Logger logger = LogManager.getLogger(BasketService.class);

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BasketItemService basketItemService;

    @Autowired
    private ProductService productService;


    public void saveBasket(User user) {
        basketRepository.save(new Basket(true, user));
    }

    @Transactional
    public Basket getActiveBasketByUserId(Long userId) {
        System.err.println("getActiveBasketByUserId" + basketRepository.findByUserIdAndActiveIsTrue(userId).getBasketItems().toString());
        return basketRepository.findByUserIdAndActiveIsTrue(userId);
    }

    //TODO написать проверки на налл в подобных
    public Basket findById(Long basketId) {
        return basketRepository.findById(basketId).get();
    }

    public void createBasket(Long userId) {
        basketRepository.save(new Basket(userService.findUserById(userId)));
    }


    public Double calculatePrice(Long basketId) {
        List<BasketItem> items = basketItemService.getAllFromBasketByBasketId(basketId);
        System.err.println("Items when calculate Price: " + items.toString());
        return items.stream().mapToDouble(BasketItem::getPrice).sum();
    }

    @Transactional
    public Basket findActiveByUserID(Long userId) {
        return basketRepository.findByUserIdAndActiveIsTrue(userId);
    }

    @Transactional
    @Modifying
    public void addProductToBasket(User user, String productName, String comment, Short baseId) {//TODO что-то тут мб
        Basket userBasket = getActiveBasketByUserId(user.getId());
        System.err.println("After get active User basket: " + userBasket.toString() + " items:" + userBasket.getBasketItems().toString());
        Product product = productService.findByNameAndBaseId(productName, baseId);
        System.err.println("Product to insert to active basket: " + product.toString());
        basketItemService.addItem(userBasket, product, comment);
        System.err.println("addProductToBasket Aftes save item to basket" + userBasket.getBasketItems().toString());
        basketRepository.save(userBasket);
        System.err.println("addProductToBasket Aftes save basket" + userBasket.getBasketItems().toString());
        //
    }


    public void clearBasket(Basket basket) {

        if (!basket.getBasketItems().isEmpty()) {
            User user = basket.getUser();
            basket.setActive(false);
            basketRepository.save(basket);
            basketRepository.save(new Basket(true, user));
        } else {
            System.out.println("ALREADY empty EBAT");
        }
    }

}
