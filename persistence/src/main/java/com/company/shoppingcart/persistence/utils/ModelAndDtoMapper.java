package com.company.shoppingcart.persistence.utils;

import lombok.experimental.UtilityClass;
import org.company.shoppingcart.domain.dto.Item;
import org.company.shoppingcart.domain.dto.ShoppingCart;

import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class ModelAndDtoMapper {
    public static ShoppingCart map(com.company.shoppingcart.persistence.model.ShoppingCart shoppingCart) {
        return ShoppingCart.builder().cartItems(mapToItemDto(shoppingCart.getCartItems()))
                .cartOwner(shoppingCart.getCartOwner()).id(shoppingCart.getId())
                .totalAmount(shoppingCart.getTotalAmount()).build();
    }

    public static Set<Item> mapToItemDto(Set<com.company.shoppingcart.persistence.model.Item> cartItems) {
        return cartItems.stream().map(i -> Item.builder().id(i.getId()).itemName(i.getItemName()).units(i.getUnits())
        .price(i.getPrice()).build()).collect(Collectors.toSet());
    }

    public static com.company.shoppingcart.persistence.model.ShoppingCart map(ShoppingCart shoppingCart) {
        return com.company.shoppingcart.persistence.model.ShoppingCart.builder().cartItems(mapToItem(shoppingCart.getCartItems()))
                .cartOwner(shoppingCart.getCartOwner()).id(shoppingCart.getId())
                .totalAmount(shoppingCart.getTotalAmount()).build();
    }

    private static Set<com.company.shoppingcart.persistence.model.Item> mapToItem(Set<Item> cartItems) {
        return cartItems.stream().map(i -> com.company.shoppingcart.persistence.model.Item.builder().id(i.getId()).itemName(i.getItemName()).units(i.getUnits())
                .price(i.getPrice()).build()).collect(Collectors.toSet());
    }
}
