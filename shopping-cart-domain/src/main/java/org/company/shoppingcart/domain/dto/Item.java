package org.company.shoppingcart.domain.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class Item {
    private Long id;
    private String itemName;
    private Integer units;
    private BigDecimal price;
}
