package org.company.shoppingcart.domain.service.impl;

import org.company.shoppingcart.domain.dto.ShoppingCart;
import org.company.shoppingcart.domain.service.CachingService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryShoppingCartCachingService implements CachingService<String, ShoppingCart> {

    private Map<String, ShoppingCart> cache = new HashMap<>();

    @Override
    public boolean doesCartExists(String cartOwner) {
        return cache.containsKey(cartOwner);
    }

    @Override
    public Optional<ShoppingCart> get(String cartOwner) {
        return Optional.ofNullable(cache.get(cartOwner));
    }

    @Override
    public void put(ShoppingCart cart) {
        cache.put(cart.getCartOwner(), cart);
    }

    @Override
    public Optional<ShoppingCart> remove(String cartOwner) {
        return Optional.ofNullable(cache.remove(cartOwner));
    }
}
