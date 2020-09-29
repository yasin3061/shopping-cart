package com.company.shoppingcart.persistence.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shopping_cart")
@Data @Builder
public class ShoppingCart {
    private static final HashSet<Item> EMPTY_ITEMS = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cartOwner;

    @OneToMany
    private Set<Item> cartItems;
    private BigDecimal totalAmount;

    public Set<Item> getCartItems() {
        if (cartItems != null) {
            return new HashSet<>(cartItems);
        } else {
            return EMPTY_ITEMS;
        }
    }

    public void setCartItems(Set<Item> cartItems) {
        if (cartItems != null) {
            this.cartItems = new HashSet<>(cartItems);
        } else {
            this.cartItems = EMPTY_ITEMS;
        }
    }
}
