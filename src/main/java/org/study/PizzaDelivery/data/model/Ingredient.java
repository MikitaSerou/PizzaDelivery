package org.study.PizzaDelivery.data.model;

import com.sun.istack.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.study.PizzaDelivery.controller.UserController;
import org.study.PizzaDelivery.data.enums.IngredientType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Ingredient {

    private static final Logger logger = LogManager.getLogger(Ingredient.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "ingredient_type", nullable = false)
    private IngredientType ingredientType;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "product_ingredient",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public Ingredient() {
    }

    public Ingredient(String name, double price, IngredientType ingredientType) {
        this.name = name;
        this.price = price;
        this.ingredientType = ingredientType;

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

    public IngredientType getType() {
        return ingredientType;
    }

    public void setType(IngredientType ingredientType) {
        this.ingredientType = ingredientType;
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
        Ingredient that = (Ingredient) o;
        return id == that.id && name.equals(that.name) && price.equals(that.price) &&
                ingredientType == that.ingredientType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, ingredientType);
    }

    @Override
    public String toString() {
        return "\nIngredient[Id:" + id +
                ", name: " + name +
                ", type: " + ingredientType +
                ", price: " + price + "]";
    }
}
