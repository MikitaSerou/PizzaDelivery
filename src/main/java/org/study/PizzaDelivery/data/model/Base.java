package org.study.PizzaDelivery.data.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;


    @OneToMany(mappedBy = "base", fetch = FetchType.LAZY)
    private List<Product> products;

    public Base() {
    }

    public Base(String name, double price) {
        this.name = name;
        this.price = price;

    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Base{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", products=" + products +
                '}';
    }
}
