package com.company.shoppingcart.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.company")
@EnableJpaRepositories(basePackages = "com.company")
@EntityScan("com.company")
public class ShoppingCartApp {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApp.class, args);
    }
}
