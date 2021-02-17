package org.study.PizzaDelivery.data.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.PizzaDelivery.controller.UserController;
import org.study.PizzaDelivery.data.repository.RoleRepository;

@Service
public class RoleService {

    private static final Logger logger = LogManager.getLogger(RoleService.class);

    @Autowired
    private RoleRepository roleRepository;

}
