package org.study.PizzaDelivery.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.enums.Status;
import org.study.PizzaDelivery.enums.TypeOfPayment;
import org.study.PizzaDelivery.model.Basket;
import org.study.PizzaDelivery.model.Order;
import org.study.PizzaDelivery.model.User;
import org.study.PizzaDelivery.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

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


    @Transactional
    public Long getCount() {
        logger.info("Call method: getCount()");

        return orderRepository.count();
    }

    @Transactional
    public List<Order> findOrdersByUserId(Long userId) {
        logger.info("Call method: findOrdersByUserId(userId: " + userId + ")");

        return orderRepository.findAllByUserId(userId);
    }

    public Order findLastOrderOfUserByUserId(Long userId) {
        logger.info("Call method: findLastOrderOfUserByUserId(userId: " + userId + ")");

        return orderRepository.findLastOrderOfUserByUserId(userId);
    }

    @Transactional
    public List<Order> findOrdersByStatus(Status status) {
        logger.info("Call method: findOrdersByStatus(status: " + status + ")");

        return orderRepository.findAllByStatus(status);
    }

    public Order addOrder(User user, String phoneNumber, String comment, TypeOfPayment typeOfPayment) {
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

        return order;
    }


    public void cancelOrder(Long orderId) {
        logger.info("Call method: cancelOrder(orderId: " + orderId + ")");
        Optional<Order> order = orderRepository.findById(orderId);
        order.ifPresentOrElse(
                o -> {
                    logger.info(order);
                    if (o.getStatus() == Status.CANCELED) {
                        return;
                    }
                    o.setStatus(Status.CANCELED);
                    orderRepository.save(o);
                },
                () -> logger.error("Order with this id: " + orderId + " is not exist."));
    }


    public void paidUpOrder(Long orderId) {
        logger.info("Call method: paidUpOrder(orderId: " + orderId + ")");
        Optional<Order> order = orderRepository.findById(orderId);
        order.ifPresentOrElse(
                o -> {
                    logger.info(order);
                    if (o.getStatus() == Status.PAID) {
                        return;
                    }
                    o.setStatus(Status.PAID);
                    orderRepository.save(o);
                },
                () -> logger.error("Order with this id: " + orderId + " is not exist."));
    }

    public void setOrderNotPaidStatus(Long orderId) {
        logger.info("Call method: setOrderNotPaidStatus(orderId: " + orderId + ")");
        Optional<Order> order = orderRepository.findById(orderId);
        order.ifPresentOrElse(
                o -> {
                    logger.info(order);
                    if (o.getStatus() == Status.NOT_PAID) {
                        return;
                    }
                    o.setStatus(Status.NOT_PAID);
                    orderRepository.save(o);
                },
                () -> logger.error("Order with this id: " + orderId + " is not exist."));
    }

    public void safeDeleteOrder(Order order) {
        logger.info("Call method: safeDeleteOrder(order: " + order + ")");

        if (order.getStatus() == Status.NOT_PAID) {
            order.setStatus(Status.CANCELED);
        }

        order.setUser((User) userService.loadUserByUsername("Удаленный"));
        orderRepository.save(order);
    }
}
