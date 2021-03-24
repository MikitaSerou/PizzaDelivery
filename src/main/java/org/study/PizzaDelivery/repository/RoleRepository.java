package org.study.PizzaDelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


    public Role findByName(String name);
}
