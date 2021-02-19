package org.study.PizzaDelivery.data.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.study.PizzaDelivery.controller.UserController;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_item")
public class OrderItem {

    private static final Logger logger = LogManager.getLogger(OrderItem.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "price")//FORMULA???
    private Double price;

    @Column(name = "description")
    private String description;

    public OrderItem() {
    }

    public OrderItem(Order order, Product product) {
        this.order = order;
        this.product = product;
        this.price = product.getPrice();
    }

    public OrderItem(Order order, Product product, Double price, String comment) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return id == orderItem.id && order.equals(orderItem.order) && product.equals(orderItem.product) &&
                price.equals(orderItem.price) && description.equals(orderItem.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, product, price, description);
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
