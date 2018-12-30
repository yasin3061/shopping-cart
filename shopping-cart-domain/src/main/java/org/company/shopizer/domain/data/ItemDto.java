package org.company.shopizer.domain.data;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data @Builder
public class ItemDto {
    private Long id;
    private String itemName;
    private Integer units;
    private BigDecimal price;
}
