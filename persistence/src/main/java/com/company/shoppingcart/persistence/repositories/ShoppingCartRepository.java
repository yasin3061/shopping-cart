package com.company.shoppingcart.persistence.repositories;

import com.company.shoppingcart.persistence.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByCartOwner(String cartOwner);
}
