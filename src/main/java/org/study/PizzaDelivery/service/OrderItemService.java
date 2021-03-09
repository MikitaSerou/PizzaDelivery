package org.study.PizzaDelivery.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.PizzaDelivery.model.Basket;
import org.study.PizzaDelivery.model.BasketItem;
import org.study.PizzaDelivery.model.Order;
import org.study.PizzaDelivery.model.OrderItem;
import org.study.PizzaDelivery.repository.OrderItemRepository;

@Service
public class OrderItemService {

    private static final Logger logger = LogManager.getLogger(OrderItemService.class);

    @Autowired
    private OrderItemRepository orderItemRepository;


    public void addOrderItemsFromBasket(Basket basket, Order order) {
        logger.info("Call method: addOrderItemsFromBasket(ingredientName: " + basket +
                ", order: " + order + ")");

        for (BasketItem basketItem :
                basket.getBasketItems()) {
            OrderItem item = new OrderItem(order, basketItem.getProduct(),
                    basketItem.getPrice(), basketItem.getComment());
            logger.info("Save new item to order: " + item);
            orderItemRepository.save(item);
        }
    }
}
