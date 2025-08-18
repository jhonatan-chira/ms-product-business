package com.nttdata.product.management.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String productId;
    private String name;
    private ProductTypeDTO productType;
}
