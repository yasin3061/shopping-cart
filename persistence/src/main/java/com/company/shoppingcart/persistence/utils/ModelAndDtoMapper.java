package com.company.shoppingcart.persistence.utils;

import com.company.shoppingcart.persistence.model.Item;
import com.company.shoppingcart.persistence.model.ShoppingCart;
import lombok.experimental.UtilityClass;
import org.company.shoppingcart.domain.data.ItemDto;
import org.company.shoppingcart.domain.data.ShoppingCartDto;

import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class ModelAndDtoMapper {
    public static ShoppingCartDto map(ShoppingCart shoppingCart) {
        return ShoppingCartDto.builder().cartItems(mapToItemDto(shoppingCart.getCartItems()))
                .cartOwner(shoppingCart.getCartOwner()).id(shoppingCart.getId())
                .totalAmount(shoppingCart.getTotalAmount()).build();
    }

    public static Set<ItemDto> mapToItemDto(Set<Item> cartItems) {
        return cartItems.stream().map(i -> ItemDto.builder().id(i.getId()).itemName(i.getItemName()).units(i.getUnits())
        .price(i.getPrice()).build()).collect(Collectors.toSet());
    }

    public static ShoppingCart map(ShoppingCartDto shoppingCart) {
        return ShoppingCart.builder().cartItems(mapToItem(shoppingCart.getCartItems()))
                .cartOwner(shoppingCart.getCartOwner()).id(shoppingCart.getId())
                .totalAmount(shoppingCart.getTotalAmount()).build();
    }

    private static Set<Item> mapToItem(Set<ItemDto> cartItems) {
        return cartItems.stream().map(i -> Item.builder().id(i.getId()).itemName(i.getItemName()).units(i.getUnits())
                .price(i.getPrice()).build()).collect(Collectors.toSet());
    }
}
