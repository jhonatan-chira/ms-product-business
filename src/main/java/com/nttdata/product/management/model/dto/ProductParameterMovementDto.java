package com.nttdata.product.management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductParameterMovementDto {
    private String movementType;
    private String period;
    private Integer quantity;
    private List<Integer> movementDays;
}
