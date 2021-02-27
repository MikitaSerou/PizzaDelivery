package org.study.PizzaDelivery.data.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.study.PizzaDelivery.controller.UserController;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Category implements Comparable<Category>{

    private static final Logger logger = LogManager.getLogger(Category.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category",  fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Product> products;

    @Column(name="category_price", nullable = false)
    private Double price;

    public Category() {
    }

    public Category(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id && Double.compare(category.price, price) == 0 && name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Category o) {

        if (this.getPrice() < o.getPrice()) return -1;
        if (this.getPrice()  > o.getPrice()) return 1;
        return 0;

       // return (int) this.price - o.getPrice();
    }
}
