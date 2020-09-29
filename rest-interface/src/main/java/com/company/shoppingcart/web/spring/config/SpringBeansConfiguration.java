package com.company.shoppingcart.web.spring.config;

import com.company.shoppingcart.persistence.connector.ShoppingCartPersistenceGatewayConnector;
import com.company.shoppingcart.persistence.repositories.ShoppingCartRepository;
import org.company.shoppingcart.domain.dto.ShoppingCart;
import org.company.shoppingcart.domain.persistence.ShoppingCartPersistenceGateway;
import org.company.shoppingcart.domain.service.CachingService;
import org.company.shoppingcart.domain.service.ShoppingCartService;
import org.company.shoppingcart.domain.service.impl.InMemoryShoppingCartCachingService;
import org.company.shoppingcart.domain.service.impl.ShoppingCartImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeansConfiguration {

    @Bean
    public CachingService<String, ShoppingCart> getCachingService() {
        return new InMemoryShoppingCartCachingService();
    }

    @Bean
    public ShoppingCartPersistenceGateway getShoppingCartPersistenceGateway(ShoppingCartRepository repository) {
        return new ShoppingCartPersistenceGatewayConnector(repository);
    }

    @Bean
    public ShoppingCartService getShoppingCartService(CachingService<String, ShoppingCart> cache,
                                                      ShoppingCartPersistenceGateway gateway) {
        return new ShoppingCartImpl(cache, gateway);
    }
}
