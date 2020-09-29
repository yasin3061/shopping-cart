package com.company.shoppingcart.web.controllers;

import com.company.shoppingcart.web.request.ShoppingCartRequest;
import com.company.shoppingcart.web.response.ShoppingCartResponse;
import com.company.shoppingcart.web.mapper.DtoToRequestMapper;
import org.company.shoppingcart.domain.dto.ShoppingCartDto;
import org.company.shoppingcart.domain.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {

    private final ShoppingCartService cartService;

    public ShoppingCartController(ShoppingCartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{cartOwner}")
    public ResponseEntity<ShoppingCartResponse> getShoppingCart(@PathVariable("cartOwner") String cartOwner) {
        Optional<ShoppingCartDto> cartFound = cartService.findByCartOwner(cartOwner);

        if (cartFound.isPresent()) {
            ShoppingCartDto cartDto = cartFound.get();
            return ResponseEntity.ok(DtoToRequestMapper.map(cartDto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity createShoppingCart(@RequestBody ShoppingCartRequest request) {
        try {
            return ResponseEntity.ok(DtoToRequestMapper.map(cartService.createNewShoppingCart(DtoToRequestMapper.map
                    (request))));
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body(iae.getMessage());
        }
    }
}
