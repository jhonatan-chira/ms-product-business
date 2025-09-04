package com.nttdata.product.management.service.impl;

import static java.util.Objects.isNull;

import com.nttdata.product.management.exception.FriendlyException;
import com.nttdata.product.management.model.dto.ProductDto;
import com.nttdata.product.management.model.dto.ProductParameterDto;
import com.nttdata.product.management.repository.ProductRepository;
import com.nttdata.product.management.repository.ProductTypeRepository;
import com.nttdata.product.management.service.ProductService;
import com.nttdata.product.management.service.mapper.ProductServiceMapper;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Implementación del servicio para la gestión de productos.
 * Proporciona operaciones CRUD y de negocio relacionadas con productos.
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductServiceMapper mapper;
  private final ProductTypeRepository productTypeRepository;

  /**
   * Crea un nuevo producto.
   *
   * @param productDto el DTO del producto a crear
   * @return un Mono que emite el DTO del producto creado
   */
  @Override
  public Mono<ProductDto> create(ProductDto productDto) {
    return productTypeRepository.findByProductTypeId(productDto.getProductType().getProductTypeId())
        .switchIfEmpty(new FriendlyException("ProductType not found with id: "
            + productDto.getProductType().getProductTypeId()).buildAsMono())
        .map(mapper::toProductTypeDto)
        .zipWith(
            Mono.just(productDto),
            mapper::toProductDto)
        .map(mapper::toProductEntity)
        .doOnNext(productEntity -> {
          if (isNull(productEntity.getProductId())) {
            productEntity.setProductId(UUID.randomUUID());
          }
        })
        .flatMap(productRepository::save)
        .map(mapper::toProductDto);
  }

  /**
   * Obtiene todos los productos activos.
   *
   * @return un Flux que emite los DTOs de los productos activos
   */
  @Override
  public Flux<ProductDto> getAll() {
    return productRepository.findByStatus(true)
        .map(mapper::toProductDto);
  }

  /**
   * Obtiene un producto por su ID.
   *
   * @param productId el ID del producto a buscar
   * @return un Mono que emite el DTO del producto encontrado
   */
  @Override
  public Mono<ProductDto> getProductById(UUID productId) {
    return productRepository.findById(productId)
        .switchIfEmpty(
            Mono.error(new NotFoundException("Product not found with id: " + productId)))
        .map(mapper::toProductDto);
  }

  /**
   * Actualiza un producto existente.
   *
   * @param productId  el ID del producto a actualizar
   * @param productDto el DTO con los datos actualizados
   * @return un Mono que emite el DTO del producto actualizado
   */
  @Transactional
  @Override
  public Mono<ProductDto> update(UUID productId, ProductDto productDto) {
    return productRepository.findById(productId)
        .switchIfEmpty(
            Mono.error(new NotFoundException("Product not found with id: " + productId)))
        .zipWith(
            productTypeRepository.findByProductTypeId(
                    productDto.getProductType().getProductTypeId())
                .switchIfEmpty(
                    Mono.error(new NotFoundException("ProductType not found"))),
            (existingProduct, productType) -> {
              mapper.toProductEntity(existingProduct, productDto);
              return existingProduct;
            })
        .flatMap(productRepository::save)
        .map(mapper::toProductDto);
  }

  /**
   * Actualiza los parámetros de un producto.
   *
   * @param productId            el ID del producto a actualizar
   * @param productParameterDtos la lista de parámetros a actualizar
   * @return un Mono que emite el DTO del producto actualizado
   */
  @Override
  public Mono<ProductDto> updateParameters(
      UUID productId, List<ProductParameterDto> productParameterDtos) {
    return productRepository.findById(productId)
        .switchIfEmpty(
            Mono.error(new NotFoundException("Product not found with id: " + productId)))
        .map(existingProduct -> {
          existingProduct.setParameters(
              mapper.toProductParameterEntities(productParameterDtos));
          return existingProduct;
        })
        .flatMap(productRepository::save)
        .map(mapper::toProductDto);
  }

  /**
   * Elimina físicamente un producto por su ID.
   *
   * @param productId el ID del producto a eliminar
   * @return un Mono que indica la finalización de la operación
   */
  @Override
  public Mono<Void> deletePhysical(UUID productId) {
    return productRepository.deleteById(productId);
  }

  /**
   * Elimina lógicamente un producto por su ID.
   * Cambia el estado del producto a inactivo.
   *
   * @param productId el ID del producto a eliminar
   * @return un Mono que emite el DTO del producto eliminado lógicamente
   */
  @Override
  public Mono<ProductDto> deleteLogical(UUID productId) {
    return productRepository.findById(productId)
        .switchIfEmpty(Mono.error(new NotFoundException(
            "Product not found with id: " + productId)))
        .flatMap(product -> {
          product.setStatus(false);
          return productRepository.save(product);
        })
        .map(mapper::toProductDto);
  }
}