package com.company.shoppingcart.web.mapper;

import com.company.shoppingcart.web.request.ItemRequest;
import com.company.shoppingcart.web.request.ShoppingCartRequest;
import com.company.shoppingcart.web.response.ShoppingCartResponse;
import org.company.shoppingcart.domain.dto.Item;
import org.company.shoppingcart.domain.dto.ShoppingCart;

import java.util.Set;
import java.util.stream.Collectors;

public class DtoToRequestMapper {
    public static ShoppingCartResponse map(ShoppingCart cartDto) {
        return ShoppingCartResponse.builder().id(cartDto.getId()).cartOwner(cartDto.getCartOwner())
                .totalAmount(cartDto.getTotalAmount()).cartItems(mapItemDtoToRequest(cartDto.getCartItems())).build();
    }

    private static Set<ItemRequest> mapItemDtoToRequest(Set<Item> cartItems) {
        return cartItems.stream().map(i -> ItemRequest.builder().id(i.getId()).itemName(i.getItemName())
                .units(i.getUnits()).price(i.getPrice()).build()).collect(Collectors.toSet());
    }

    public static ShoppingCart map(ShoppingCartRequest cartRequest) {
        return ShoppingCart.builder().cartOwner(cartRequest.getCartOwner())
                .cartItems(mapItemRequestToDto(cartRequest.getCartItems())).build();
    }

    private static Set<Item> mapItemRequestToDto(Set<ItemRequest> cartItems) {
        return cartItems.stream().map(i -> Item.builder().id(i.getId()).itemName(i.getItemName())
                .units(i.getUnits()).price(i.getPrice()).build()).collect(Collectors.toSet());
    }
}
