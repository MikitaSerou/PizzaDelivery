package org.study.PizzaDelivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.PizzaDelivery.model.Role;
import org.study.PizzaDelivery.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;


    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null); //TODO везде orElse(null)
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
