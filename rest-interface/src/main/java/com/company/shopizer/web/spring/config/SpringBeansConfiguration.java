package com.company.shopizer.web.spring.config;

import com.company.shopizer.persistence.connector.ShoppingCartPersistenceGatewayConnector;
import com.company.shopizer.persistence.repositories.ShoppingCartRepository;
import org.company.shopizer.domain.data.ShoppingCartDto;
import org.company.shopizer.domain.persistence.ShoppingCartPersistenceGateway;
import org.company.shopizer.domain.service.CachingService;
import org.company.shopizer.domain.service.ShoppingCartService;
import org.company.shopizer.domain.service.impl.InMemoryShoppingCartCachingService;
import org.company.shopizer.domain.service.impl.ShoppingCartImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeansConfiguration {

    @Bean
    public CachingService<String, ShoppingCartDto> getCachingService() {
        return new InMemoryShoppingCartCachingService();
    }

    @Bean
    public ShoppingCartPersistenceGateway getShoppingCartPersistenceGateway(ShoppingCartRepository repository) {
        return new ShoppingCartPersistenceGatewayConnector(repository);
    }

    @Bean
    public ShoppingCartService getShoppingCartService(CachingService<String, ShoppingCartDto> cache,
                                                      ShoppingCartPersistenceGateway gateway) {
        return new ShoppingCartImpl(cache, gateway);
    }
}
