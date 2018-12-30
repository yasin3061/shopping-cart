package org.company.shopizer.domain.service;

import org.company.shopizer.domain.data.ShoppingCartDto;

import java.util.Optional;

// K is the Key and V is the Value
public interface CachingService<K, V> {
    boolean doesCartExists(K cartOwner);
    Optional<ShoppingCartDto> get(K cartOwner);
    void put(V cart);
    Optional<V> remove(K cartOwner);
}
