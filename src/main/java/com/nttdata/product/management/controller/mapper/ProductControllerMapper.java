package com.nttdata.product.management.controller.mapper;

import com.nttdata.product.management.model.api.CreateProductRequest;
import com.nttdata.product.management.model.api.CreateProductResponse;
import com.nttdata.product.management.model.api.DeactivateProductResponse;
import com.nttdata.product.management.model.api.GetProductsResponse;
import com.nttdata.product.management.model.api.ProductParameter;
import com.nttdata.product.management.model.api.ProductType;
import com.nttdata.product.management.model.api.ReplaceProductRequest;
import com.nttdata.product.management.model.dto.ProductDto;
import com.nttdata.product.management.model.dto.ProductParameterDto;
import com.nttdata.product.management.model.dto.ProductTypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre entidades de dominio y objetos de transferencia de datos (DTOs)
 * relacionados con productos. Utiliza MapStruct para la generación automática de implementaciones.
 */
@Component
@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    imports = {java.time.ZoneOffset.class,
        java.time.OffsetDateTime.class}
)
public interface ProductControllerMapper {

  /**
   * Convierte un ProductDto en una respuesta de creación de producto.
   *
   * @param productDto DTO del producto.
   * @return Respuesta de creación de producto.
   */
  CreateProductResponse toCreateProductResponse(ProductDto productDto);

  /**
   * Convierte una solicitud de creación de producto en un DTO de producto.
   *
   * @param createProductRequest Solicitud de creación de producto.
   * @return DTO del producto.
   */
  ProductDto toProductDto(CreateProductRequest createProductRequest);

  /**
   * Convierte una solicitud de reemplazo de producto en un DTO de producto.
   *
   * @param request Solicitud de reemplazo de producto.
   * @return DTO del producto.
   */
  ProductDto toProductDto(ReplaceProductRequest request);

  /**
   * Convierte un ProductDto en una respuesta de obtención de productos.
   *
   * @param productDto DTO del producto.
   * @return Respuesta de obtención de productos.
   */
  GetProductsResponse toGetProductsResponse(ProductDto productDto);

  /**
   * Convierte un ProductType en un DTO de tipo de producto.
   *
   * @param productType Tipo de producto.
   * @return DTO de tipo de producto.
   */
  ProductTypeDto toDto(ProductType productType);

  /**
   * Convierte un DTO de tipo de producto en un ProductType.
   *
   * @param productTypeDto DTO de tipo de producto.
   * @return Tipo de producto.
   */
  ProductType toEntity(ProductTypeDto productTypeDto);

  /**
   * Convierte un ProductDto en una respuesta de desactivación de producto.
   * Incluye un mensaje personalizado.
   *
   * @param productDto DTO del producto.
   * @return Respuesta de desactivación de producto.
   */
  @Mapping(target = "message", expression = "java(\"Producto desactivado exitosamente\")")
  DeactivateProductResponse toDeactivateProductResponse(ProductDto productDto);

  /**
   * Convierte un parámetro de producto en un DTO de parámetro de producto.
   *
   * @param productParameter Parámetro del producto.
   * @return DTO de parámetro de producto.
   */
  ProductParameterDto toProductParameterDto(ProductParameter productParameter);
}