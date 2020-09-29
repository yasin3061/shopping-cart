package com.company.shoppingcart.persistence.connector;

import com.company.shoppingcart.persistence.model.ShoppingCart;
import com.company.shoppingcart.persistence.repositories.ShoppingCartRepository;
import com.company.shoppingcart.persistence.utils.ModelAndDtoMapper;
import org.company.shoppingcart.domain.data.ShoppingCartDto;
import org.company.shoppingcart.domain.persistence.ShoppingCartPersistenceGateway;

import java.util.Objects;
import java.util.Optional;

public class ShoppingCartPersistenceGatewayConnector implements ShoppingCartPersistenceGateway {

    private final ShoppingCartRepository repository;

    public ShoppingCartPersistenceGatewayConnector(ShoppingCartRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ShoppingCartDto> findByCartOwner(String cartOwner) {
        Optional<ShoppingCart> fromDb = repository.findByCartOwner(cartOwner);

        if (fromDb.isPresent()) {
            ShoppingCart shoppingCart = fromDb.get();
            ShoppingCartDto shoppingCartDto = ModelAndDtoMapper.map(shoppingCart);
            return Optional.of(shoppingCartDto);
        }
        return Optional.empty();
    }

    @Override
    public ShoppingCartDto save(ShoppingCartDto cart) {
        return ModelAndDtoMapper.map(repository.save(Objects.requireNonNull(ModelAndDtoMapper.map(cart))));
    }
}
