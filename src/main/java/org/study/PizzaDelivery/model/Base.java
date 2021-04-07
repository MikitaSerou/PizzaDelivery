package org.study.PizzaDelivery.model;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price_multiplier", nullable = false)

    private Double priceMultiplier;

    @OneToMany(mappedBy = "base", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> products;


    public Base() {
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

    public Double getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(Double priceMultiplier) {
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

        if (id != base.id) return false;
        if (name != null ? !name.equals(base.name) : base.name != null) return false;
        return priceMultiplier != null ? priceMultiplier.equals(base.priceMultiplier) : base.priceMultiplier == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (priceMultiplier != null ? priceMultiplier.hashCode() : 0);
        return result;
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
