package org.study.PizzaDelivery.data.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.study.PizzaDelivery.controller.UserController;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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


    public Basket() {

    }

    public Basket(boolean isActive, User user) {
        this.isActive = isActive;
        this.user = user;
        this.time = LocalDateTime.now();
    }

    public Basket(User user) {
        this.user = user;
        this.isActive = true;
        this.time = LocalDateTime.now();
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
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", user=" + user.getId() +
                '}';
    }


}
