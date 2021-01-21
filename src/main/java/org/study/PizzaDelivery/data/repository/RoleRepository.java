package org.study.PizzaDelivery.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.study.PizzaDelivery.data.model.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
