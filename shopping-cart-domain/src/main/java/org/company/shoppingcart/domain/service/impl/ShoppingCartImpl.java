package org.company.shoppingcart.domain.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.company.shoppingcart.domain.dto.Item;
import org.company.shoppingcart.domain.dto.ShoppingCart;
import org.company.shoppingcart.domain.persistence.ShoppingCartPersistenceGateway;
import org.company.shoppingcart.domain.service.CachingService;
import org.company.shoppingcart.domain.service.ShoppingCartService;
import org.springframework.util.CollectionUtils;

import java.util.Optional;
import java.util.Set;

public class ShoppingCartImpl implements ShoppingCartService {

    private final CachingService<String, ShoppingCart> cache;
    private final ShoppingCartPersistenceGateway persistence;

    public ShoppingCartImpl(CachingService<String, ShoppingCart> cache, ShoppingCartPersistenceGateway persistence) {
        this.cache = cache;
        this.persistence = persistence;
    }

    @Override
    public ShoppingCart createNewShoppingCart(ShoppingCart cart) {
        validateShoppingCart(cart);
        Optional<ShoppingCart> shoppingCart = cache.get(cart.getCartOwner());
        shoppingCart.ifPresent(c -> {throw new IllegalArgumentException("A cart owned by " + cart.getCartOwner()
                + " already exists");});
        cache.put(cart);
        return cart;
    }

    @Override
    public ShoppingCart updateItemsInShoppingCart(String cartOwner, Set<Item> newItemsList) {
        Optional<ShoppingCart> shoppingCartFound = cache.get(cartOwner);
        ShoppingCart cart = shoppingCartFound.orElseThrow(() -> new IllegalArgumentException("Shopping cart " +
                cartOwner + " not found"));

        cart.setCartItems(newItemsList);

        return cart;
    }

    @Override
    public Optional<ShoppingCart> dropShoppingCart(String cartOwner) {
        return cache.remove(cartOwner);
    }

    @Override
    public ShoppingCart save(String cartOwner) {
        Optional<ShoppingCart> toBeSaved = cache.get(cartOwner);

        if (toBeSaved.isPresent()) {
            return persistence.save(toBeSaved.get());
        } else {
            throw new IllegalArgumentException("Shopping cart with owner " + cartOwner + " not found");
        }
    }

    @Override
    public Optional<ShoppingCart> findByCartOwner(String cartOwner) {
        Optional<ShoppingCart> cartFromDb = persistence.findByCartOwner(cartOwner);
        if (cartFromDb.isPresent()) {
            return cartFromDb;
        } else {
            return cache.get(cartOwner);
        }
    }

    private void validateShoppingCart(ShoppingCart cart) {
        if (StringUtils.isBlank(cart.getCartOwner())) {
            throw new IllegalArgumentException("Cart owner's name not provided");
        }

        if (CollectionUtils.isEmpty(cart.getCartItems())) {
            throw new IllegalArgumentException("At least one item is required to create the cart");
        }
    }
}
