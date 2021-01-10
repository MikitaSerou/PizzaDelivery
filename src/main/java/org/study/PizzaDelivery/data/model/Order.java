package org.study.PizzaDelivery.data.model;

import com.sun.istack.NotNull;
import org.study.PizzaDelivery.data.enums.Status;
import org.study.PizzaDelivery.data.enums.TypeOfPayment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "price")
    private double totalPrice;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeOfPayment typeOfPayment;

    @Column(name = "time_of_order", nullable = false)
    private LocalDateTime time;

    @Column(name = "comment")
    private String comment;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL})
    private List<Item> orders_items;

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
                 String comment, List<Item> orders_items) {
        this.user = user;
        this.phoneNumber = phoneNumber;
        this.totalPrice = orders_items.stream().mapToDouble(Item::getPrice).sum(); //???? is it work???
        this.typeOfPayment = typeOfPayment;
        this.time = LocalDateTime.now();
        this.comment = comment;
        this.status = Status.NOT_PAID;
        this.orders_items = orders_items;
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
            Order.this.totalPrice = totalPrice;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double price) {
        this.totalPrice = price;
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

    public List<Item> getOrders_items() {
        return orders_items;
    }

    public void setOrders_items(List<Item> orders_items) {
        this.orders_items = orders_items;
        this.totalPrice = orders_items.stream()
                .mapToDouble(Item::getPrice).sum(); // проверить на правильность расчета!!!
    }

    @Override
    public String toString() {
        return "\nOrder[Id: " + id +
                ", userid: " + user.getId() +
                ", phone: " + phoneNumber +
                ", price: " + totalPrice +
                ", type of payment: " + typeOfPayment +
                ", order time: " + time +
                ", status: " + status + "]";
    }
}
