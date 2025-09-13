package com.nttdata.product.management.service.mapper;

import com.nttdata.product.management.model.dto.ProductDto;
import com.nttdata.product.management.model.dto.ProductParameterDto;
import com.nttdata.product.management.model.dto.ProductParameterMovementDto;
import com.nttdata.product.management.model.dto.ProductTypeDto;
import com.nttdata.product.management.model.entity.ProductEntity;
import com.nttdata.product.management.model.entity.ProductParameterEntity;
import com.nttdata.product.management.model.entity.ProductParameterMovementEntity;
import com.nttdata.product.management.model.entity.ProductTypeEntity;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

/**
 * Mapper interface for converting between DTOs and entities related to products.
 * Utilizes MapStruct for automatic mapping and transformation.
 */
@Component
@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    imports = {java.time.ZoneOffset.class,
        java.time.OffsetDateTime.class}
)

public interface ProductServiceMapper {

  /**
   * Converts a ProductDto object to a ProductEntity object.
   *
   * @param productDto the ProductDto to convert
   * @return the resulting ProductEntity
   */
  ProductEntity toProductEntity(ProductDto productDto);

  /**
   * Updates an existing ProductEntity with values from a ProductDto.
   * Only specific fields are mapped.
   *
   * @param productEntity the target ProductEntity to update
   * @param productDto    the source ProductDto with updated values
   * @return the updated ProductEntity
   */
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "name", source = "productDto.name")
  @Mapping(target = "status", source = "productDto.status")
  @Mapping(target = "parameters", source = "productDto.parameters")
  @Mapping(target = "productType", source = "productDto.productType")
  ProductEntity toProductEntity(@MappingTarget ProductEntity productEntity, ProductDto productDto);

  /**
   * Converts a ProductTypeDto object to a ProductTypeEntity object.
   *
   * @param productTypeDto the ProductTypeDto to convert
   * @return the resulting ProductTypeEntity
   */
  ProductTypeEntity toProductTypeEntity(ProductTypeDto productTypeDto);

  /**
   * Converts a ProductTypeEntity object to a ProductTypeDto object.
   *
   * @param productTypeEntity the ProductTypeEntity to convert
   * @return the resulting ProductTypeDto
   */
  ProductTypeDto toProductTypeDto(ProductTypeEntity productTypeEntity);

  /**
   * Converts a ProductEntity object to a ProductDto object.
   *
   * @param productEntity the ProductEntity to convert
   * @return the resulting ProductDto
   */
  ProductDto toProductDto(ProductEntity productEntity);

  /**
   * Updates an existing ProductDto with values from a ProductTypeDto.
   * Only specific fields are mapped.
   *
   * @param productTypeDto the source ProductTypeDto with updated values
   * @param productDto     the target ProductDto to update
   * @return the updated ProductDto
   */
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "productDto.productType", source = "productTypeDto")
  ProductDto toProductDto(ProductTypeDto productTypeDto, @MappingTarget ProductDto productDto);

  /**
   * Converts a list of ProductParameterDto objects to a list of ProductParameterEntity objects.
   *
   * @param dto the list of ProductParameterDto to convert
   * @return the resulting list of ProductParameterEntity
   */
  List<ProductParameterEntity> toProductParameterEntities(List<ProductParameterDto> dto);

  /**
   * Converts a list of ProductParameterMovementDto objects to a list
   * of ProductParameterMovementEntity objects.
   * This method is used internally by MapStruct for nested list mappings.
   *
   * @param dto the list of ProductParameterMovementDto to convert
   * @return the resulting list of ProductParameterMovementEntity
   */
  List<ProductParameterMovementEntity> toProductParameterMovementEntities(
      List<ProductParameterMovementDto> dto);

}
