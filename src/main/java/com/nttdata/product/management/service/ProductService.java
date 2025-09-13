package com.nttdata.product.management.service;

import com.nttdata.product.management.model.dto.ProductDto;
import com.nttdata.product.management.model.dto.ProductParameterDto;
import java.util.List;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Servicio para la gestión de productos.
 * Proporciona métodos para crear, obtener, actualizar, eliminar y gestionar parámetros de
 * productos.
 */
public interface ProductService {

  /**
   * Crea un nuevo producto.
   *
   * @param productDto DTO del producto a crear.
   * @return Un Mono que emite el producto creado.
   */
  Mono<ProductDto> create(ProductDto productDto);

  /**
   * Obtiene todos los productos.
   *
   * @return Un Flux que emite los productos existentes.
   */
  Flux<ProductDto> getAll();

  /**
   * Obtiene un producto por su identificador.
   *
   * @param productId Identificador único del producto.
   * @return Un Mono que emite el producto correspondiente al identificador.
   */
  Mono<ProductDto> getProductById(UUID productId);

  /**
   * Actualiza un producto existente.
   *
   * @param productId  Identificador único del producto.
   * @param productDto DTO con los datos actualizados del producto.
   * @return Un Mono que emite el producto actualizado.
   */
  Mono<ProductDto> update(UUID productId, ProductDto productDto);

  /**
   * Actualiza los parámetros de un producto.
   *
   * @param productId            Identificador único del producto.
   * @param productParameterDtos Lista de DTOs de parámetros del producto.
   * @return Un Mono que emite el producto con los parámetros actualizados.
   */
  Mono<ProductDto> updateParameters(UUID productId, List<ProductParameterDto> productParameterDtos);

  /**
   * Elimina físicamente un producto.
   *
   * @param productId Identificador único del producto.
   * @return Un Mono que completa cuando el producto ha sido eliminado.
   */
  Mono<Void> deletePhysical(UUID productId);

  /**
   * Elimina lógicamente un producto.
   *
   * @param productId Identificador único del producto.
   * @return Un Mono que emite el producto eliminado lógicamente.
   */
  Mono<ProductDto> deleteLogical(UUID productId);

}