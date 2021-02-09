package org.study.PizzaDelivery.data.model;


import javax.persistence.*;

@Entity
@Table(name = "basket_item")
public class BasketItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "basket_id", nullable = false)
    private Basket basket;

    @ManyToOne(fetch = FetchType.EAGER)  //, cascade = {CascadeType.ALL}
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name="price")
    private double price;

    @Column(name = "description")
    private String description;

    public BasketItem() {
    }

    public BasketItem(Basket basket, Product product, double price, String description) {
        this.basket = basket;
        this.product = product;
        this.price = price;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getComment() {
        return description;
    }

    public void setComment(String comment) {
        this.description = comment;
    }

    @Override
    public String toString() {
        return "BasketItem{" +
                "id=" + id +
                ", basket=" + basket.getId() +
                ", product=" + product.getId() +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
