package org.study.PizzaDelivery.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Dough {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @OneToMany(mappedBy = "dough", fetch = FetchType.LAZY)
    private List<Pizza> pizzas;
}
