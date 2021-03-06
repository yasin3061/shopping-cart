package com.company.shoppingcart.web.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ShoppingCartRequest {
    private static final HashSet<ItemRequest> EMPTY_SET = new HashSet<>();

    private String cartOwner;
    private Set<ItemRequest> cartItems;

    public Set<ItemRequest> getCartItems() {
        if (cartItems != null) {
            return new HashSet<>(cartItems);
        } else {
            return EMPTY_SET;
        }
    }

    public void setCartItems(Set<ItemRequest> cartItems) {
        if (cartItems != null) {
            this.cartItems = new HashSet<>(cartItems);
        } else {
            this.cartItems = EMPTY_SET;
        }
    }
}
