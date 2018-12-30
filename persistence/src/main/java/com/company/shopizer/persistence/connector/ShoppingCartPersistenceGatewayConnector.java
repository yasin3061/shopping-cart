package com.company.shopizer.persistence.connector;

import com.company.shopizer.persistence.model.ShoppingCart;
import com.company.shopizer.persistence.repositories.ShoppingCartRepository;
import com.company.shopizer.persistence.utils.ModelAndDtoMapper;
import org.company.shopizer.domain.data.ShoppingCartDto;
import org.company.shopizer.domain.persistence.ShoppingCartPersistenceGateway;

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
