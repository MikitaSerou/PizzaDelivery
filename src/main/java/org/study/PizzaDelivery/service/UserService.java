package org.study.PizzaDelivery.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.PizzaDelivery.model.Basket;
import org.study.PizzaDelivery.model.Role;
import org.study.PizzaDelivery.model.User;
import org.study.PizzaDelivery.repository.RoleRepository;
import org.study.PizzaDelivery.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Autowired(required = false)
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private BasketService basketService;

    @Autowired
    private OrderService orderService;


    public User findByName(String userName) {
        logger.info("Call method: findByName(userName: " + userName + ")");

        return userRepository.findByUsername(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        logger.info("Call method: loadUserByUsername(userName: " + userName + ")");
        User user = userRepository.findByUsername(userName);
        if (user == null) {

            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Transactional
    public User findUserById(Long userId) {
        logger.info("Call method: findUserById(userId: " + userId + ")");

        Optional<User> userFromDb = userRepository.findById(userId);

        return userFromDb.orElse(new User());
    }

    @Transactional
    public List<User> findAll() {
        logger.info("Call method: findAll()");

        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        logger.info("Call method: saveUser(user: " + user + ")");
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            logger.error("User: " + user + " already exist!");
            return false;
        }

        user.setRoles(Collections.singleton(
                new Role(1L, "ROLE_USER")));
        logger.info("Set role...");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        logger.info("Encrypt password...");
        user.setMail(user.getMail());
        logger.info("Set Email: " + user.getMail());
        user.setPhoneNumber(user.getPhoneNumber());
        logger.info("Set Phone Number: " + user.getPhoneNumber());
        userRepository.save(user);
        logger.info("Saved user: " + user);

        logger.info("Create Basket for User...");
        basketService.saveBasket(userRepository.findByUsername(user.getUsername()));

        return true;
    }

    public boolean deleteUser(Long userId) {
        logger.info("Call method: deleteUser(userId: " + userId + ")");

        if (userRepository.findById(userId).isPresent()) {
            logger.info("Deleting user with ID: " + userId);
            orderService.findOrdersByUserId(userId).forEach(o -> {
                orderService.safeDeleteOrder(o);
            });
            userRepository.deleteById(userId);
            return true;
        } else {
            logger.error("user with ID: " + userId + " is not exist.");
        }

        return false;
    }

}
