package org.company.shoppingcart.domain.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
public class ShoppingCart {
    private static final HashSet<Item> EMPTY_SET = new HashSet<>();

    private Long id;
    private String cartOwner;
    private Set<Item> cartItems;
    private BigDecimal totalAmount;

    public Set<Item> getCartItems() {
        if (cartItems != null) {
            return new HashSet<>(cartItems);
        } else {
            return EMPTY_SET;
        }
    }

    public void setCartItems(Set<Item> cartItems) {
        if (cartItems != null) {
            this.cartItems = new HashSet<>(cartItems);
        } else {
            this.cartItems = EMPTY_SET;
        }
    }
}
