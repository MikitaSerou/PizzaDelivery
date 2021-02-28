package org.study.PizzaDelivery.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Product {

    private static final Logger logger = LogManager.getLogger(Product.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "base_id", nullable = false)
    private Base base;

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "product_ingredient",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private List<BasketItem> basketItems;


    public Product() {
    }

    // Builder -->
    public class Builder {

        private Builder() {
            logger.info("new Builder");
        }

        public Builder name(String name) {
            logger.info("Builder set name: " + name);
            Product.this.name = name;
            return this;
        }

        public Builder price(Double price) {
            logger.info("Builder set price: " + price);
            Product.this.price = price;
            return this;
        }

        public Builder ingredients(List<Ingredient> ingredients) {
            logger.info("Builder set ingredients: " + ingredients);
            Product.this.ingredients = ingredients;
            return this;
        }

        public Builder describe(String describe) {
            logger.info("Builder set describe: " + describe);
            Product.this.description = describe;
            return this;
        }

        public Builder base(Base base) {
            logger.info("Builder set base: " + base);
            Product.this.base = base;
            return this;
        }

        public Builder category(Category category) {
            logger.info("Builder set category: " + category);
            Product.this.category = category;
            return this;
        }

        public Product build() {
            logger.info("Builder build: " + Product.this);
            return Product.this;
        }

    }
    // <-- Builder

    public static Builder builder() {
        logger.info("Product().new Builder()");
        return new Product().new Builder();
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && name.equals(product.name) && category.equals(product.category) &&
                base.equals(product.base) && price.equals(product.price) && description.equals(product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, base, price, description);
    }

    @Override
    public String toString() {
        return "\nProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category.getName() +
                ", base=" + base.getName() +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}

