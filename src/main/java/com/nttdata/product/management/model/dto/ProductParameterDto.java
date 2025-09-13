package com.nttdata.product.management.model.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ProductParameterDto.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductParameterDto {
  private String customerProfile;
  private Integer maximumContracts;
  private Integer maximumHolders;
  private Integer maximumAuthorizedSignatories;
  private BigDecimal maintenanceCommission;
  private Boolean creditLimitRequired;
  private BigDecimal creditLimit;
  private List<String> customerType;
  private List<ProductParameterMovementDto> movementParameters;
}