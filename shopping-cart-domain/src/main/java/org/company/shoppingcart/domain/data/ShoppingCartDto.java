package org.company.shoppingcart.domain.data;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data @Builder
public class ShoppingCartDto {
    private static final HashSet<ItemDto> EMPTY_SET = new HashSet<>();

    private Long id;
    private String cartOwner;
    private Set<ItemDto> cartItems;
    private BigDecimal totalAmount;

    public Set<ItemDto> getCartItems() {
        if (cartItems != null) {
            return new HashSet<>(cartItems);
        } else {
            return EMPTY_SET;
        }
    }

    public void setCartItems(Set<ItemDto> cartItems) {
        if (cartItems != null) {
            this.cartItems = new HashSet<>(cartItems);
        } else {
            this.cartItems = EMPTY_SET;
        }
    }
}
