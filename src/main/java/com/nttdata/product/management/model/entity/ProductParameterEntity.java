package com.nttdata.product.management.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductParameterEntity {
    private String customerProfile;
    private Integer maximumContracts;
    private Integer maximumHolders;
    private Integer maximumAuthorizedSignatories;
    private BigDecimal maintenanceCommission;
    private Boolean creditLimitRequired;
    private BigDecimal creditLimit;
    private List<String> customerType;
    private List<ProductParameterMovementEntity> movementParameters;
}