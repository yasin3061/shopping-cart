package org.company.shopizer.domain.persistence;

import org.company.shopizer.domain.data.ShoppingCartDto;

import java.util.Optional;

public interface ShoppingCartPersistenceGateway {
    Optional<ShoppingCartDto> findByCartOwner(String cartOwner);
    ShoppingCartDto save(ShoppingCartDto cart);
}
