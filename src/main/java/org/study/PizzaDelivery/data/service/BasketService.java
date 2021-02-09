package org.study.PizzaDelivery.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private UserService userService;

    @Autowired
    BasketItemService basketItemService;

    @Autowired
    ProductService productService;


    public void saveBasket(User user) {
        basketRepository.save(new Basket(true, user));
    }

    @Transactional
    public Basket getActiveBasketByUserId(Long userId) {
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
        return items.stream().mapToDouble(BasketItem::getPrice).sum();
    }

    @Transactional
    public Basket findActiveByUserID(Long userId) {
        return basketRepository.findByUserIdAndActiveIsTrue(userId);
    }

    @Transactional
    @Modifying
    public void addProductToBasket(User user, String productName, String comment, Short baseId) {
        System.out.println(user.getId() + " " + productName + " "+ comment + " " + baseId);
        Basket userBasket = getActiveBasketByUserId(user.getId());
        Product product = productService.findByNameAndBaseId(productName, baseId);
        System.out.println(product);
        basketItemService.addItem(userBasket, product, comment);
    }


    public void clearBasket(Long basketId) {
        Basket basketForClear = basketRepository.findById(basketId).get();
        if (!basketForClear.getBasketItems().isEmpty()) {
            User user = basketForClear.getUser();
            basketForClear.setActive(false);
            basketRepository.save(basketForClear);
            basketRepository.save(new Basket(true, user));
        } else {
            System.out.println("ALREADY empty EBAT");
        }
    }

}
