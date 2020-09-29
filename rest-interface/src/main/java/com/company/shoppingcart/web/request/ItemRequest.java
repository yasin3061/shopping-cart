package com.company.shoppingcart.web.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ItemRequest {
    private Long id;
    private String itemName;
    private Integer units;
    private BigDecimal price;
}
