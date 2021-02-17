package org.study.PizzaDelivery.data.model;

import jakarta.validation.constraints.Size;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.study.PizzaDelivery.controller.UserController;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Base {

    private static final Logger logger = LogManager.getLogger(Base.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price_multiplier", nullable = false)
    @Size(min=1, max=2, message = "Only 1-2")
    private Double priceMultiplier;


    @OneToMany(mappedBy = "base", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> products;

    public Base() {
    }

    public Base(String name, double price) {
        this.name = name;
        this.priceMultiplier = price;

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

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return id == base.id && Double.compare(base.priceMultiplier, priceMultiplier) == 0 && name.equals(base.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, priceMultiplier, products);
    }

    @Override
    public String toString() {
        return "Base{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + priceMultiplier +
                '}';
    }
}
