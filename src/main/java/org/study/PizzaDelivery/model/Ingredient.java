package org.study.PizzaDelivery.model;

import com.sun.istack.NotNull;
import org.study.PizzaDelivery.enums.IngredientType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Ingredient {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

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

    public Ingredient(String name, Double price, IngredientType ingredientType) {
        this.name = name;
        this.price = price;
        this.ingredientType = ingredientType;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public IngredientType getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(IngredientType ingredientType) {
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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return ingredientType == that.ingredientType;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (ingredientType != null ? ingredientType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\nIngredient[Id:" + id +
                ", name: " + name +
                ", type: " + ingredientType +
                ", price: " + price + "]";
    }
}
