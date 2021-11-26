package org.study.PizzaDelivery.model;

import com.sun.istack.NotNull;
import org.study.PizzaDelivery.enums.Status;
import org.study.PizzaDelivery.enums.TypeOfPayment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "PRICE")
    private Double price;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_OF_PAYMENT")
    private TypeOfPayment typeOfPayment;

    @Column(name = "TIME", nullable = false)
    private LocalDateTime time;

    @Column(name = "COMMENT")
    private String comment;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
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

    public Order(User user, String phoneNumber, Double price, TypeOfPayment typeOfPayment,
                 String comment) {
        this.user = user;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.typeOfPayment = typeOfPayment;
        this.time = LocalDateTime.parse(LocalDateTime.now().format(FORMATTER), FORMATTER);
        this.comment = comment;
        this.status = Status.NOT_PAID;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public DateTimeFormatter getFORMATTER() {
        return FORMATTER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(order.phoneNumber) : order.phoneNumber != null) return false;
        if (price != null ? !price.equals(order.price) : order.price != null) return false;
        if (typeOfPayment != order.typeOfPayment) return false;
        if (time != null ? !time.equals(order.time) : order.time != null) return false;
        if (comment != null ? !comment.equals(order.comment) : order.comment != null) return false;
        return status == order.status;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (typeOfPayment != null ? typeOfPayment.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
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
