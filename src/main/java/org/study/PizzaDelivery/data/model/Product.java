package org.study.PizzaDelivery.data.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "base_id")
    private Base base;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "product_ingredient",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE)
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<BasketItem> basketItems;


    public Product() {
    }


    // Builder -->
    public class Builder {

        private Builder() {

        }

        public Builder name(String name) {
            Product.this.name = name;
            return this;
        }

        public Builder price(Double price) {
            Product.this.price = price;
            return this;
        }

        public Builder describe(String describe) {
            Product.this.description = describe;
            return this;
        }

        public Builder base(Base base) {
            Product.this.base = base;
            return this;
        }

        public Builder category(Category category) {
            Product.this.category = category;
            return this;
        }

        public Product build() {
            return Product.this;
        }

    }

    public static Builder builder() {
        return new Product().new Builder();
    }

// <-- Builder


    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<OrderItem> getOrders_items() {
        return orderItems;
    }

    public void setOrders_items(List<OrderItem> orders_Order_items) {
        this.orderItems = orders_Order_items;
    }


    @Override
    public String toString() {
        String str;
        if (base != null) {
            str = "\nProduct[Id: " + id +
                    ", name: " + name +
                    ", category:(" + category.getId() + "|" + category.getName() + ")" +
                    ", dough:(" + base.getId() + "|" + base.getName() + "|" + base.getName() + ")" +
                    ", price: " + price + "]";
        } else {
            str = "\nProduct[Id: " + id +
                    ", name: " + name +
                    ", category:(" + category.getId() + "|" + category.getName() + ")" +
                    ", price: " + price + "]";
        }
        return str;
    }

}

