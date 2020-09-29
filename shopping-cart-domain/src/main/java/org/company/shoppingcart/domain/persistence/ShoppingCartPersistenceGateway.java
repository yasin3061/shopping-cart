package org.company.shoppingcart.domain.persistence;

import org.company.shoppingcart.domain.dto.ShoppingCart;

import java.util.Optional;

public interface ShoppingCartPersistenceGateway {
    Optional<ShoppingCart> findByCartOwner(String cartOwner);
    ShoppingCart save(ShoppingCart cart);
}
