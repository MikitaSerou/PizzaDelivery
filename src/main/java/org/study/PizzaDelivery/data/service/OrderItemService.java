package org.study.PizzaDelivery.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.PizzaDelivery.data.model.Basket;
import org.study.PizzaDelivery.data.model.BasketItem;
import org.study.PizzaDelivery.data.model.Order;
import org.study.PizzaDelivery.data.model.OrderItem;
import org.study.PizzaDelivery.data.repository.OrderItemRepository;


@Service
public class OrderItemService {

    @Autowired
    private BasketService basketService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderService orderService;

    public void saveItem(OrderItem oderItem){
        orderItemRepository.save(oderItem);
    }

    public void addOrderItemsFromBasket(Long basketId, Order order) {
        Basket basket = basketService.findById(basketId);
        for (BasketItem basketItem :
                basket.getBasketItems()) {
            OrderItem item = new OrderItem(order, basketItem.getProduct(),
                    basketItem.getPrice(), basketItem.getComment());
            orderItemRepository.save(item);
        }
        orderService.saveOrder(order);
    }
}
