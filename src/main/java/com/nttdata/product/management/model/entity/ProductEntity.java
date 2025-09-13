package com.nttdata.product.management.model.entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

/**
 * ProductEntity.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document("Product")
@TypeAlias("ProductEntity")
@EqualsAndHashCode(callSuper = false)
public class ProductEntity implements Serializable {
  @Id
  @Field(targetType = FieldType.STRING, name = "_id")
  private UUID productId;
  private String name;
  private ProductTypeEntity productType;
  private boolean status = true;
  private List<ProductParameterEntity> parameters;

}
