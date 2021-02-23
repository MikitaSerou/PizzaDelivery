package org.study.PizzaDelivery.data.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "basket")
public class Basket {

    private static final Logger logger = LogManager.getLogger(Basket.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "basket", fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL})
    private List<BasketItem> basketItems;

    @Transient
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public Basket() {

    }

    public Basket(boolean isActive, User user) {
        this.isActive = isActive;
        this.user = user;
        this.time = LocalDateTime.parse(LocalDateTime.now().format(FORMATTER), FORMATTER);
    }

    public Basket(User user) {
        this.user = user;
        this.isActive = true;
        this.time = LocalDateTime.parse(LocalDateTime.now().format(FORMATTER), FORMATTER);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return id == basket.id && isActive.equals(basket.isActive) && time.equals(basket.time) && user.equals(basket.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive, time, user);
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", user=" + user.getId() +
                '}';
    }


}
