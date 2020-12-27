package org.study.PizzaDelivery.model;

import javax.persistence.*;

@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dough_id")
    private Dough dough;

    @Column(name = "price") // FORMULA
    private double price;

    @Column(name = "describe")
    private String describe;

    @Column(name = "is_vegan") //FORMULA
    private boolean is_vegan;

}
