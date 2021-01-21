package org.study.PizzaDelivery.data.model;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "price")//FORMULA???
    private double price;

    @Column(name = "description")
    private String description;

    public OrderItem() {
    }

    public OrderItem(Order order, Product product) {
        this.order = order;
        this.product = product;
        this.price = product.getPrice();
    }

    public OrderItem(Order order, Product product, double price, String comment) {
        this.order = order;
        this.product = product;
        this.price = price;
        this.description = comment;
    }

    public OrderItem(Order order, Product product, String comment) {
        this.order = order;
        this.product = product;
        this.description = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }



    public void setProduct(Product product) {
        this.product = product;
        this.price = product.getPrice();
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
        return "Item{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
