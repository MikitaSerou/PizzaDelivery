package org.study.PizzaDelivery.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "basket")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");


    public Basket() {
    }

    public Basket(Boolean isActive, User user) {
        this.isActive = isActive;
        this.user = user;
        this.time = LocalDateTime.parse(LocalDateTime.now().format(FORMATTER), FORMATTER);
    }

    public Basket(User user) {
        this.user = user;
        this.isActive = true;
        this.time = LocalDateTime.parse(LocalDateTime.now().format(FORMATTER), FORMATTER);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public DateTimeFormatter getFORMATTER() {
        return FORMATTER;
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

        if (id != basket.id) return false;
        if (isActive != null ? !isActive.equals(basket.isActive) : basket.isActive != null) return false;
        if (time != null ? !time.equals(basket.time) : basket.time != null) return false;
        return user != null ? user.equals(basket.user) : basket.user == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
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
