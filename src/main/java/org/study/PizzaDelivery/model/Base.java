package org.study.PizzaDelivery.model;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price_multiplier", nullable = false)

    private Double priceMultiplier;

    @OneToMany(mappedBy = "base", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> products;


    public Base() {

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
        return id == base.id && name.equals(base.name) && priceMultiplier.equals(base.priceMultiplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, priceMultiplier);
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
