package org.company.shoppingcart.domain.service;

import org.company.shoppingcart.domain.dto.ShoppingCart;

import java.util.Optional;

// K is the Key and V is the Value
public interface CachingService<K, V> {
    boolean doesCartExists(K cartOwner);
    Optional<ShoppingCart> get(K cartOwner);
    void put(V cart);
    Optional<V> remove(K cartOwner);
}
