package org.company.shoppingcart.domain.persistence;

import org.company.shoppingcart.domain.data.ShoppingCartDto;

import java.util.Optional;

public interface ShoppingCartPersistenceGateway {
    Optional<ShoppingCartDto> findByCartOwner(String cartOwner);
    ShoppingCartDto save(ShoppingCartDto cart);
}
