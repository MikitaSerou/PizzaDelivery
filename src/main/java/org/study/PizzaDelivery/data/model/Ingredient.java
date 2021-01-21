package org.study.PizzaDelivery.data.model;

import com.sun.istack.NotNull;
import org.study.PizzaDelivery.data.enums.Type;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "count")
    private int count;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "pizza_ingredient",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    private List<Product> products;

    public Ingredient() {
    }

    public Ingredient(String name, double price, Type type, int count) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.count = count;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    @Override
    public String toString() {
        return "\nIngredient[Id:" + id +
                ", name: " + name +
                ", type: " + type +
                ", count: " + count +
                ", price: " + price + "]";
    }
}
