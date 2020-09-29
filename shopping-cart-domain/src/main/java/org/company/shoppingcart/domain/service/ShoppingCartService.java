package org.company.shoppingcart.domain.service;

import org.company.shoppingcart.domain.dto.ItemDto;
import org.company.shoppingcart.domain.dto.ShoppingCartDto;

import java.util.Optional;
import java.util.Set;

public interface ShoppingCartService {
    ShoppingCartDto createNewShoppingCart(ShoppingCartDto cart);
    ShoppingCartDto updateItemsInShoppingCart(String cartOwner, Set<ItemDto> newItemsList);
    Optional<ShoppingCartDto> dropShoppingCart(String cartOwner);
    ShoppingCartDto save(String cartOwner);
    Optional<ShoppingCartDto> findByCartOwner(String cartOwner);
}
