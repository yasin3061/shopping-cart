package org.company.shoppingcart.domain.service.impl;

import org.company.shoppingcart.domain.data.ShoppingCartDto;
import org.company.shoppingcart.domain.service.CachingService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryShoppingCartCachingService implements CachingService<String, ShoppingCartDto> {

    private Map<String, ShoppingCartDto> cache = new HashMap<>();

    @Override
    public boolean doesCartExists(String cartOwner) {
        return cache.containsKey(cartOwner);
    }

    @Override
    public Optional<ShoppingCartDto> get(String cartOwner) {
        return Optional.ofNullable(cache.get(cartOwner));
    }

    @Override
    public void put(ShoppingCartDto cart) {
        cache.put(cart.getCartOwner(), cart);
    }

    @Override
    public Optional<ShoppingCartDto> remove(String cartOwner) {
        return Optional.ofNullable(cache.remove(cartOwner));
    }
}
