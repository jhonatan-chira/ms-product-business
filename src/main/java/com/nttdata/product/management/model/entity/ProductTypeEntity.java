package com.nttdata.product.management.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * ProductTypeEntity.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document("productType")
@EqualsAndHashCode(callSuper = false)
public class ProductTypeEntity {
  @BsonId
  private Integer productTypeId;
  private String name;
}
