package org.study.PizzaDelivery.data.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dough_id")
    private Dough dough;

    @Column(name = "price")
    private double price;

    @Column(name = "describe")
    private String describe;

    @Column(name = "is_vegan")
    private boolean isVegan;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "pizza_ingredient",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "user_pizza",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @OneToMany(mappedBy = "pizza", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<OrderItem> orders_items;



    public Pizza() {
    }


    // Builder -->
    public class Builder {

        private Builder() {

        }

        public Builder name(String name) {
            Pizza.this.name = name;
            return this;
        }

        public Builder price(Double price) {
            Pizza.this.price = price;
            return this;
        }

        public Builder describe(String describe) {
            Pizza.this.describe = describe;
            return this;
        }

        public Builder dough(Dough dough) {
            Pizza.this.dough = dough;
            return this;
        }

        public Builder category(Category category) {
            Pizza.this.category = category;
            return this;
        }

        public Builder isVegan(boolean isVegan) {
            Pizza.this.isVegan = isVegan;
            return this;
        }

        public Pizza build() {
            return Pizza.this;
        }

    }

    public static Builder builder() {
        return new Pizza().new Builder();
    }

// <-- Builder


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Dough getDough() {
        return dough;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public boolean isIsVegan() {
        return isVegan;
    }

    public void setIsVegan(boolean is_vegan) {
        this.isVegan = is_vegan;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<OrderItem> getOrders_items() {
        return orders_items;
    }

    public void setOrders_items(List<OrderItem> orders_items) {
        this.orders_items = orders_items;
    }

    @Override
    public String toString() {
        return "\nPizza[Id: " + id +
                ", name: " + name +
                ", category:("+ category.getId()+"|" + category.getName() + ")" +
                ", dough:(" + dough.getId()+"|" + dough.getName() + "|" + dough.getSize() +
                "), vegan: " + isVegan +
                ", price: " + price+ "]";
    }
}
