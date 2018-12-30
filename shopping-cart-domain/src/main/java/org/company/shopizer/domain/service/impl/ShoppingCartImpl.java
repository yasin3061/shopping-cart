package org.company.shopizer.domain.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.company.shopizer.domain.data.ItemDto;
import org.company.shopizer.domain.data.ShoppingCartDto;
import org.company.shopizer.domain.persistence.ShoppingCartPersistenceGateway;
import org.company.shopizer.domain.service.CachingService;
import org.company.shopizer.domain.service.ShoppingCartService;
import org.springframework.util.CollectionUtils;

import java.util.Optional;
import java.util.Set;

public class ShoppingCartImpl implements ShoppingCartService {

    private final CachingService<String, ShoppingCartDto> cache;
    private final ShoppingCartPersistenceGateway persistence;

    public ShoppingCartImpl(CachingService<String, ShoppingCartDto> cache, ShoppingCartPersistenceGateway persistence) {
        this.cache = cache;
        this.persistence = persistence;
    }

    @Override
    public ShoppingCartDto createNewShoppingCart(ShoppingCartDto cart) {
        validateShoppingCart(cart);
        Optional<ShoppingCartDto> shoppingCart = cache.get(cart.getCartOwner());
        shoppingCart.ifPresent(c -> {throw new IllegalArgumentException("A cart owned by " + cart.getCartOwner()
                + " already exists");});
        cache.put(cart);
        return cart;
    }

    @Override
    public ShoppingCartDto updateItemsInShoppingCart(String cartOwner, Set<ItemDto> newItemsList) {
        Optional<ShoppingCartDto> shoppingCartFound = cache.get(cartOwner);
        ShoppingCartDto cart = shoppingCartFound.orElseThrow(() -> new IllegalArgumentException("Shopping cart " +
                cartOwner + " not found"));

        cart.setCartItems(newItemsList);

        return cart;
    }

    @Override
    public Optional<ShoppingCartDto> dropShoppingCart(String cartOwner) {
        return cache.remove(cartOwner);
    }

    @Override
    public ShoppingCartDto save(String cartOwner) {
        Optional<ShoppingCartDto> toBeSaved = cache.get(cartOwner);

        if (toBeSaved.isPresent()) {
            return persistence.save(toBeSaved.get());
        } else {
            throw new IllegalArgumentException("Shopping cart with owner " + cartOwner + " not found");
        }
    }

    @Override
    public Optional<ShoppingCartDto> findByCartOwner(String cartOwner) {
        Optional<ShoppingCartDto> cartFromDb = persistence.findByCartOwner(cartOwner);
        if (cartFromDb.isPresent()) {
            return cartFromDb;
        } else {
            return cache.get(cartOwner);
        }
    }

    private void validateShoppingCart(ShoppingCartDto cart) {
        if (StringUtils.isBlank(cart.getCartOwner())) {
            throw new IllegalArgumentException("Cart owner's name not provided");
        }

        if (CollectionUtils.isEmpty(cart.getCartItems())) {
            throw new IllegalArgumentException("At least one item is required to create the cart");
        }
    }
}
