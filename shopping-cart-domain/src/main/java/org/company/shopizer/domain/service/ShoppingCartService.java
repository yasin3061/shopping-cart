package org.company.shopizer.domain.service;

import org.company.shopizer.domain.data.ItemDto;
import org.company.shopizer.domain.data.ShoppingCartDto;

import java.util.Optional;
import java.util.Set;

public interface ShoppingCartService {
    ShoppingCartDto createNewShoppingCart(ShoppingCartDto cart);
    ShoppingCartDto updateItemsInShoppingCart(String cartOwner, Set<ItemDto> newItemsList);
    Optional<ShoppingCartDto> dropShoppingCart(String cartOwner);
    ShoppingCartDto save(String cartOwner);
    Optional<ShoppingCartDto> findByCartOwner(String cartOwner);
}
