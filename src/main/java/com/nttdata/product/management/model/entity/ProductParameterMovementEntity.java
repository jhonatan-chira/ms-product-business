package com.nttdata.product.management.model.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * ProductParameterMovementEntity.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductParameterMovementEntity {

  /**
   * Tipo de movimiento asociado al parámetro del producto.
   */
  private String movementType;

  /**
   * Período en el que se realiza el movimiento.
   */
  private String period;

  /**
   * Cantidad asociada al movimiento.
   */
  private Integer quantity;

  /**
   * Lista de días en los que se realiza el movimiento.
   */
  private List<Integer> movementDays;
}