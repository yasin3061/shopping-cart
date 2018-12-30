package com.company.shopizer.web.response;

import com.company.shopizer.web.request.ItemRequest;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data @Builder
public class ShoppingCartResponse {
    private static final HashSet<ItemRequest> EMPTY_SET = new HashSet<>();

    private Long id;
    private String cartOwner;
    private Set<ItemRequest> cartItems;
    private BigDecimal totalAmount;

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
