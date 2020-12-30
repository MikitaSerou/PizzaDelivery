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

    @Column(name = "is_vegan", nullable = false)
    private boolean is_vegan;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "pizza_ingredient",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    private List<Pizza> pizzas;

    public Ingredient() {
    }

    public Ingredient(String name, double price, Type type, boolean is_vegan) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.is_vegan = is_vegan;
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

    public boolean isIs_vegan() {
        return is_vegan;
    }

    public void setIs_vegan(boolean is_vegan) {
        this.is_vegan = is_vegan;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    @Override
    public String toString() {
        return "\nIngredient[Id:" + id +
                ", name: " + name +
                ", type: " + type +
                ", vegan: " + is_vegan +
                ", price: " + price + "]";
    }
}
