package org.study.PizzaDelivery.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.enums.Status;
import org.study.PizzaDelivery.enums.TypeOfPayment;
import org.study.PizzaDelivery.model.*;

import org.study.PizzaDelivery.repository.OrderRepository;


import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private static final Logger logger = LogManager.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    UserService userService;

    @Autowired
    private BasketService basketService;

    @Autowired
    private OrderItemService orderItemService;


    @Transactional
    public Long getCount() {
        logger.info("Call method: getCount()");

        return orderRepository.count();
    }

    @Transactional
    public Order findById(Long orderId) {
        logger.info("Call method: findById(orderId: " + orderId + ")");
        Optional<Order> order = orderRepository.findById(orderId);

        order.ifPresentOrElse(
                logger::info,
                () -> {
                    logger.error("Order with this id: " + orderId + " is not exist.");
                });

        return order.get();
    }

    @Transactional
    public List<Order> findOrdersByUserId(Long userId) {
        logger.info("Call method: findOrdersByUserId(userId: " + userId + ")");

        return orderRepository.findAllByUserId(userId);
    }

    public Order findLastOrderOfUserByUserId(Long userId){
        logger.info("Call method: findLastOrderOfUserByUserId(userId: " + userId + ")");

        return  orderRepository.findLastOrderOfUserByUserId(userId);
    }

    @Transactional
    public List<Order> findNotPaidOrders() {
        logger.info("Call method: findNotPaidOrders()");

        return orderRepository.findAllByStatus(Status.NOT_PAID);
    }


    public void addOrder(User user, String phoneNumber, String comment, TypeOfPayment typeOfPayment) {
        logger.info("Call method: addOrder(user: " + user +
                ", phoneNumber: " + phoneNumber +
                ", comment: " + comment +
                ", typeOfPayment: " + typeOfPayment + ")");
        Basket basket = basketService.findActiveByUserID(user.getId());
        double orderPrice = basketService.calculatePrice(basket.getId());
        Order order = new Order(user, phoneNumber, orderPrice, typeOfPayment, comment);
        logger.info("Save new Order: " + order);
        orderRepository.save(order);
        orderItemService.addOrderItemsFromBasket(basket, order);

        basketService.clearBasket(basket);
    }


    public boolean cancelOrder(Long orderId) {
        logger.info("Call method: cancelOrder(orderId: " + orderId + ")");
        Optional<Order> order = orderRepository.findById(orderId);

        order.ifPresentOrElse(
                logger::info,
                () -> {
                    logger.error("Order with this id: " + orderId + " is not exist.");
                });

        if (order.get().getStatus() == Status.CANCELED) {
            return false;
        }

        order.get().setStatus(Status.CANCELED);
        orderRepository.save(order.get());

        return true;
    }


    public boolean paidUpOrder(Long orderId) {
        logger.info("Call method: paidUpOrder(orderId: " + orderId + ")");
        Optional<Order> order = orderRepository.findById(orderId);

        order.ifPresentOrElse(
                logger::info,
                () -> {
                    logger.error("Order with this id: " + orderId + " is not exist.");
                });

        if (order.get().getStatus() == Status.PAID) {
            return false;
        }

        order.get().setStatus(Status.PAID);
        orderRepository.save(order.get());

        return true;
    }

    public boolean setOrderNotPaidStatus(Long orderId) {
        logger.info("Call method: setOrderNotPaidStatus(orderId: " + orderId + ")");
        Optional<Order> order = orderRepository.findById(orderId);

        order.ifPresentOrElse(
                logger::info,
                () -> {
                    logger.error("Order with this id: " + orderId + " is not exist.");
                });

        if (order.get().getStatus() == Status.NOT_PAID) {
            return false;
        }

        order.get().setStatus(Status.NOT_PAID);
        orderRepository.save(order.get());

        return true;
    }

    public void safeDeleteOrder(Order order){
        logger.info("Call method: safeDeleteOrder(order: " + order + ")");
        order.setUser(userService.findByName("Удаленный"));
        orderRepository.save(order);
    }
}
