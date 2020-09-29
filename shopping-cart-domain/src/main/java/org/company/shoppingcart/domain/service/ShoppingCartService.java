package org.company.shoppingcart.domain.service;

import org.company.shoppingcart.domain.dto.Item;
import org.company.shoppingcart.domain.dto.ShoppingCart;

import java.util.Optional;
import java.util.Set;

public interface ShoppingCartService {
    ShoppingCart createNewShoppingCart(ShoppingCart cart);
    ShoppingCart updateItemsInShoppingCart(String cartOwner, Set<Item> newItemsList);
    Optional<ShoppingCart> dropShoppingCart(String cartOwner);
    ShoppingCart save(String cartOwner);
    Optional<ShoppingCart> findByCartOwner(String cartOwner);
}
