package org.study.PizzaDelivery.data.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.PizzaDelivery.controller.UserController;
import org.study.PizzaDelivery.data.model.Basket;
import org.study.PizzaDelivery.data.model.BasketItem;
import org.study.PizzaDelivery.data.model.Order;
import org.study.PizzaDelivery.data.model.OrderItem;
import org.study.PizzaDelivery.data.repository.OrderItemRepository;


@Service
public class OrderItemService {

    private static final Logger logger = LogManager.getLogger(OrderItemService.class);

    @Autowired
    private BasketService basketService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderService orderService;

    public void saveItem(OrderItem oderItem){
        orderItemRepository.save(oderItem);
    }

    public void addOrderItemsFromBasket(Basket basket, Order order) { //TODO добавляет по 3
        System.err.println(basket.getBasketItems().toString());
        for (BasketItem basketItem :
                basket.getBasketItems()) {
            System.err.println("итерация");
            OrderItem item = new OrderItem(order, basketItem.getProduct(),
                    basketItem.getPrice(), basketItem.getComment());
            orderItemRepository.save(item);
        }
        orderService.saveOrder(order);
    }
}
