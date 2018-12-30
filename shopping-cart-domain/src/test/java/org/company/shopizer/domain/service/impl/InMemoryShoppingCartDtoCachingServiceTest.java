package org.company.shopizer.domain.service.impl;

import io.github.benas.randombeans.api.EnhancedRandom;
import org.company.shopizer.domain.data.ShoppingCartDto;
import org.company.shopizer.domain.service.CachingService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class InMemoryShoppingCartDtoCachingServiceTest {

    private CachingService<String, ShoppingCartDto> cache = new InMemoryShoppingCartCachingService();
    private ShoppingCartDto randomShoppingCartDto = EnhancedRandom.random(ShoppingCartDto.class);

    @Before
    public void clearCache() {
        cache.remove(randomShoppingCartDto.getCartOwner());
    }

    @Test
    public void shouldReturnShoppingCartIfExists() {
        cache.put(randomShoppingCartDto);
        assertEquals(cache.get(randomShoppingCartDto.getCartOwner()).get(), randomShoppingCartDto);
    }

    @Test
    public void shouldReturnEmptyIfShoppingCartDoesNotExist() {
        assertFalse(cache.get(randomShoppingCartDto.getCartOwner()).isPresent());
    }

    @Test
    public void shouldReturnShoppingCartIfRemoved() {
        cache.put(randomShoppingCartDto);
        assertEquals(cache.remove(randomShoppingCartDto.getCartOwner()).get(), randomShoppingCartDto);
    }

    @Test
    public void shouldReturnEmptyShoppingCartWhenShoppingCartDoesNotExistsS() {
        assertFalse(cache.remove(randomShoppingCartDto.getCartOwner()).isPresent());
    }
}