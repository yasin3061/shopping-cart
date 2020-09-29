package com.company.shoppingcart.persistence.connector;

import com.company.shoppingcart.persistence.repositories.ShoppingCartRepository;
import com.company.shoppingcart.persistence.utils.ModelAndDtoMapper;
import org.company.shoppingcart.domain.dto.ShoppingCart;
import org.company.shoppingcart.domain.persistence.ShoppingCartPersistenceGateway;

import java.util.Objects;
import java.util.Optional;

public class ShoppingCartPersistenceGatewayConnector implements ShoppingCartPersistenceGateway {

    private final ShoppingCartRepository repository;

    public ShoppingCartPersistenceGatewayConnector(ShoppingCartRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ShoppingCart> findByCartOwner(String cartOwner) {
        Optional<com.company.shoppingcart.persistence.model.ShoppingCart> fromDb = repository.findByCartOwner(cartOwner);

        if (fromDb.isPresent()) {
            com.company.shoppingcart.persistence.model.ShoppingCart shoppingCart = fromDb.get();
            ShoppingCart shoppingCartDto = ModelAndDtoMapper.map(shoppingCart);
            return Optional.of(shoppingCartDto);
        }
        return Optional.empty();
    }

    @Override
    public ShoppingCart save(ShoppingCart cart) {
        return ModelAndDtoMapper.map(repository.save(Objects.requireNonNull(ModelAndDtoMapper.map(cart))));
    }
}
