package org.study.PizzaDelivery.data.model;

import com.sun.istack.NotNull;
import org.study.PizzaDelivery.data.enums.Status;
import org.study.PizzaDelivery.data.enums.TypeOfPayment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "price")
    private Double price;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_payment")
    private TypeOfPayment typeOfPayment;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Column(name = "comment")
    private String comment;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE})
    private List<OrderItem> orderItems;

    public Order() {
        this.time = LocalDateTime.now();
        this.status = Status.NOT_PAID;
    }

    public Order(User user, String phoneNumber, TypeOfPayment typeOfPayment, String comment) {
        this.user = user;
        this.phoneNumber = phoneNumber;
        this.typeOfPayment = typeOfPayment;
        this.time = LocalDateTime.now();
        this.comment = comment;
        this.status = Status.NOT_PAID;
    }

    public Order(User user, String phoneNumber, TypeOfPayment typeOfPayment,
                 String comment, List<OrderItem> orderItems) {
        this.user = user;
        this.phoneNumber = phoneNumber;
        this.price = orderItems.stream().mapToDouble(OrderItem::getPrice).sum(); //???? is it work???
        this.typeOfPayment = typeOfPayment;
        this.time = LocalDateTime.now();
        this.comment = comment;
        this.status = Status.NOT_PAID;
        this.orderItems = orderItems;
    }

    public Order(User user, String phoneNumber, double price, TypeOfPayment typeOfPayment,
                 String comment) {
        this.user = user;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.typeOfPayment = typeOfPayment;
        this.time = LocalDateTime.now();
        this.comment = comment;
        this.status = Status.NOT_PAID;
    }


    // Builder -->
    public class Builder {

        private Builder() {

        }

        public Order.Builder user(User user) {
            Order.this.user = user;
            return this;
        }

        public Order.Builder totalPrice(Double totalPrice) {
            Order.this.price = totalPrice;
            return this;
        }

        public Order.Builder comment(String comment) {
            Order.this.comment = comment;
            return this;
        }

        public Order.Builder phone(String phoneNumber) {
            Order.this.phoneNumber = phoneNumber;
            return this;
        }

        public Order.Builder typeOfPayment(TypeOfPayment typeOfPayment) {
            Order.this.typeOfPayment = typeOfPayment;
            return this;
        }

        public Order.Builder time(LocalDateTime time) {
            Order.this.time = time;
            return this;
        }

        public Order.Builder status(Status status) {
            Order.this.status = status;
            return this;
        }

        public Order build() {
            return Order.this;
        }

    }

    public static Order.Builder builder() {
        return new Order().new Builder();
    }

// <-- Builder


    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TypeOfPayment getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(TypeOfPayment typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<OrderItem> getOrders_items() {
        return orderItems;
    }

    public void setOrders_items(List<OrderItem> orders_Order_items) {
        this.orderItems = orders_Order_items;
        this.price = orders_Order_items.stream()
                .mapToDouble(OrderItem::getPrice).sum(); // проверить на правильность расчета!!!
    }

    @Override
    public String toString() {
        return "\nOrder[Id: " + id +
                ", userid: " + user.getId() +
                ", phone: " + phoneNumber +
                ", price: " + price +
                ", type of payment: " + typeOfPayment +
                ", order time: " + time +
                ", status: " + status + "]";
    }
}
