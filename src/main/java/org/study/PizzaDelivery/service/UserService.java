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
import org.study.PizzaDelivery.enums.Status;
import org.study.PizzaDelivery.model.Basket;
import org.study.PizzaDelivery.model.Role;
import org.study.PizzaDelivery.model.User;
import org.study.PizzaDelivery.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

        return userFromDb.orElse(null);
    }

    @Transactional
    public List<User> findAll() {
        logger.info("Call method: findAll()");

        return (List<User>) userRepository.findAll();
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
        logger.info("Create Basket for User...");
        user.setBaskets(Collections.singletonList(new Basket(true, user)));
        userRepository.save(user);
        logger.info("Saved user: " + user);

        return true;
    }

    @Transactional
    public void deleteUser(Long userId) {
        logger.info("Call method: deleteUser(userId: " + userId + ")");
        Optional<User> userForDelete = userRepository.findById(userId);
        userForDelete.ifPresentOrElse(
                u -> {
                    logger.info("Deleting user with ID: " + userId);
                    u.getOrders().forEach(o -> {
                        logger.info("Safe Delete Order: " + o + ")");
                        if (o.getStatus() == Status.NOT_PAID) {
                            o.setStatus(Status.CANCELED);
                        }
                        o.setUser((User) loadUserByUsername("Удаленный"));
                    });
                    logger.info("Deleting user: " + u);
                    userRepository.deleteById(userId);
                },
                () -> logger.error("User with ID: " + userId + " is not exist.")
        );
    }
}
