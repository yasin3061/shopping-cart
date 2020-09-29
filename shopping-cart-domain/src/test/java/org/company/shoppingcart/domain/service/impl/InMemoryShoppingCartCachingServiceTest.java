package org.company.shoppingcart.domain.service.impl;

import io.github.benas.randombeans.api.EnhancedRandom;
import org.company.shoppingcart.domain.dto.ShoppingCart;
import org.company.shoppingcart.domain.service.CachingService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class InMemoryShoppingCartCachingServiceTest {

    private CachingService<String, ShoppingCart> cache = new InMemoryShoppingCartCachingService();
    private ShoppingCart randomShoppingCart = EnhancedRandom.random(ShoppingCart.class);

    @Before
    public void clearCache() {
        cache.remove(randomShoppingCart.getCartOwner());
    }

    @Test
    public void shouldReturnShoppingCartIfExists() {
        cache.put(randomShoppingCart);
        assertEquals(cache.get(randomShoppingCart.getCartOwner()).get(), randomShoppingCart);
    }

    @Test
    public void shouldReturnEmptyIfShoppingCartDoesNotExist() {
        assertFalse(cache.get(randomShoppingCart.getCartOwner()).isPresent());
    }

    @Test
    public void shouldReturnShoppingCartIfRemoved() {
        cache.put(randomShoppingCart);
        assertEquals(cache.remove(randomShoppingCart.getCartOwner()).get(), randomShoppingCart);
    }

    @Test
    public void shouldReturnEmptyShoppingCartWhenShoppingCartDoesNotExistsS() {
        assertFalse(cache.remove(randomShoppingCart.getCartOwner()).isPresent());
    }
}
