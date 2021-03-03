package org.study.PizzaDelivery.model;

import com.sun.istack.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.study.PizzaDelivery.enums.Status;
import org.study.PizzaDelivery.enums.TypeOfPayment;
import org.study.PizzaDelivery.utils.Formatter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    private static final Logger logger = LogManager.getLogger(Order.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    @Transient
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");


    public Order() {
        this.time = LocalDateTime.parse(LocalDateTime.now().format(FORMATTER), FORMATTER);
        this.status = Status.NOT_PAID;
    }

    public Order(User user, String phoneNumber, TypeOfPayment typeOfPayment, String comment) {
        this.user = user;
        this.phoneNumber = phoneNumber;
        this.typeOfPayment = typeOfPayment;
        this.time = LocalDateTime.parse(LocalDateTime.now().format(FORMATTER), FORMATTER);
        this.comment = comment;
        this.status = Status.NOT_PAID;
    }

    public Order(User user, String phoneNumber, TypeOfPayment typeOfPayment,
                 String comment, List<OrderItem> orderItems) {
        this.user = user;
        this.phoneNumber = phoneNumber;
        this.price = orderItems.stream().mapToDouble(OrderItem::getPrice).sum(); //???? is it work???
        this.typeOfPayment = typeOfPayment;
        this.time = LocalDateTime.parse(LocalDateTime.now().format(FORMATTER), FORMATTER);
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
        this.time = LocalDateTime.parse(LocalDateTime.now().format(FORMATTER), FORMATTER);
        this.comment = comment;
        this.status = Status.NOT_PAID;
    }


    // Builder -->
    public class Builder {

        private Builder() {
            logger.info("new Builder");
        }

        public Order.Builder user(User user) {
            logger.info("Builder set user: " + user);
            Order.this.user = user;
            return this;
        }

        public Order.Builder totalPrice(Double totalPrice) {
            logger.info("Builder set totalPrice: " + totalPrice);
            Order.this.price = totalPrice;
            return this;
        }

        public Order.Builder comment(String comment) {
            logger.info("Builder set comment: " + comment);
            Order.this.comment = comment;
            return this;
        }

        public Order.Builder phone(String phoneNumber) {
            logger.info("Builder set phoneNumber: " + phoneNumber);
            Order.this.phoneNumber = phoneNumber;
            return this;
        }

        public Order.Builder typeOfPayment(TypeOfPayment typeOfPayment) {
            logger.info("Builder set typeOfPayment: " + typeOfPayment);
            Order.this.typeOfPayment = typeOfPayment;
            return this;
        }

        public Order.Builder time(LocalDateTime time) {
            logger.info("Builder set time: " + time);
            Order.this.time = LocalDateTime.parse(time.format(FORMATTER), FORMATTER);
            return this;
        }

        public Order.Builder status(Status status) {
            logger.info("Builder set status: " + status);
            Order.this.status = status;
            return this;
        }

        public Order build() {
            logger.info("Builder build: " + Order.this);
            return Order.this;
        }

    }
    // <-- Builder

    public static Order.Builder builder() {
        logger.info("Order().new Builder()");
        return new Order().new Builder();
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && user.equals(order.user) && phoneNumber.equals(order.phoneNumber) &&
                price.equals(order.price) && typeOfPayment == order.typeOfPayment && time.equals(order.time) &&
                comment.equals(order.comment) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, phoneNumber, price, typeOfPayment, time, comment, status);
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
