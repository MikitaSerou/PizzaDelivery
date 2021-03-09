package org.study.PizzaDelivery.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements UserDetails {

    private static final Logger logger = LogManager.getLogger(User.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_name", nullable = false)
    @NotEmpty(message = "{user.username.empty}")
    @Size(min = 3, max =30, message = "{user.username.size}")
    private String username;

    @Column(name = "user_password", nullable = false)
    @NotEmpty(message = "{user.password.empty}")
    @Size(min = 8, message = "{user.password.size}")
    private String password;

    @Column(name = "e_mail", nullable = false)
    @Email(message = "{user.email.invalid}")
    @NotEmpty(message = "{user.email.empty}")
    private String mail;

    @Column(name = "phone_number")
    @NotEmpty(message = "{user.phoneNumber.empty}")
    @Pattern(regexp="^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$",
            message="{user.phoneNumber.pattern}")
    private String phoneNumber;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Basket> baskets;

    @Transient
    @NotEmpty(message = "{user.passwordConfirm.empty}")
    @Size(min = 8, message = "{user.passwordConfirm.size}")
    private String passwordConfirm;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Order> orders;


    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username,
                String password,
                Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User(String username,
                String password,
                String mail,
                String phoneNumber,
                Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Basket> getBaskets() {
        return baskets;
    }

    public void setBaskets(List<Basket> baskets) {
        this.baskets = baskets;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        logger.info("getAuthorities()");
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        logger.info("isAccountNonExpired()");
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        logger.info("isAccountNonLocked()");
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        logger.info("isCredentialsNonExpired()");
        return true;
    }

    @Override
    public boolean isEnabled() {
        logger.info("isEnabled()");
        return true;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && username.equals(user.username) && password.equals(user.password) &&
                mail.equals(user.mail) && phoneNumber.equals(user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, mail, phoneNumber);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", roles=" + roles +
                '}';
    }
}
