package org.company.shoppingcart.domain.data;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ItemDto {
    private Long id;
    private String itemName;
    private Integer units;
    private BigDecimal price;
}
