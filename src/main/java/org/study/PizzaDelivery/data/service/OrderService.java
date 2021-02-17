package org.study.PizzaDelivery.data.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.PizzaDelivery.controller.UserController;
import org.study.PizzaDelivery.data.enums.Status;
import org.study.PizzaDelivery.data.enums.TypeOfPayment;
import org.study.PizzaDelivery.data.model.*;

import org.study.PizzaDelivery.data.repository.OrderRepository;


import java.util.List;

@Service
public class OrderService {

    private static final Logger logger = LogManager.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BasketService basketService;

    @Autowired
    private OrderItemService orderItemService;

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public Order findById(Long orderId) {
        return orderRepository.findById(orderId).get();
    }

    public List<Order> findOrdersByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public List<Order> findAllByUserUsername(String userName) {
        return orderRepository.findAllByUserUsername(userName);
    }

    public List<Order> findNotPaidOrders() {
        return orderRepository.findAllByStatus(Status.NOT_PAID);
    }


    public void addOrder(Basket basket, String phoneNumber, String comment, TypeOfPayment typeOfPayment) {
        System.err.println("01Basket befor do order: " + basket.getBasketItems().toString());
        User user = basket.getUser();
        System.err.println("01Basket befor do order: " + basket.getBasketItems().toString());
        double orderPrice = basketService.calculatePrice(basket.getId());
        Order order = new Order(user, phoneNumber, orderPrice, typeOfPayment, comment);
        orderRepository.save(order);
        //System.err.println(basketService.findById(basketId).getBasketItems().toString());
        System.err.println("02Basket befor do order: " + basket.getBasketItems().toString());
        orderItemService.addOrderItemsFromBasket(basket, order);
        basketService.clearBasket(basket);
    }



    public boolean cancelOrder(Long orderId) {
        System.out.println("OS CANCEL");
        Order order = orderRepository.findById(orderId).get();
        if (order.getStatus() == Status.CANCELED) {
            return false;
        }
        order.setStatus(Status.CANCELED);
        orderRepository.save(order);
        return true;
    }


    public boolean paidUpOrder(Long orderId) {
        System.out.println("OS PAID");
        Order order = orderRepository.findById(orderId).get();
        if (order.getStatus() == Status.PAID) {
            return false;
        }
        order.setStatus(Status.PAID);
        orderRepository.save(order);
        return true;
    }

    public boolean setOrdernotPaidStatus(Long orderId) {
        System.out.println("OS NOT PAID");
        Order order = orderRepository.findById(orderId).get();
        if (order.getStatus() == Status.NOT_PAID) {
            return false;
        }
        order.setStatus(Status.NOT_PAID);
        orderRepository.save(order);
        return true;
    }
}
