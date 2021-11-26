package org.study.PizzaDelivery.model;

import javax.persistence.*;

@Entity
@Table(name = "BASKET_ITEM")
public class BasketItem {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "BASKET_ID", nullable = false)
    private Basket basket;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)  //, cascade = {CascadeType.ALL}
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Column(name = "DESCRIPTION")
    private String description;


    public BasketItem() {
    }

    public BasketItem(Basket basket, Product product, Double price, String description) {
        this.basket = basket;
        this.product = product;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getComment() {
        return description;
    }

    public void setComment(String comment) {
        this.description = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasketItem that = (BasketItem) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (basket != null ? !basket.equals(that.basket) : that.basket != null) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (basket != null ? basket.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BasketItem{" +
                "id=" + id +
                ", basket=" + basket.getId() +
                ", product=(id: " + product.getId() +
                ", name: " + product.getName() +
                "), price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
