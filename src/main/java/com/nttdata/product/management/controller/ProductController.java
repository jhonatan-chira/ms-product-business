package com.nttdata.product.management.controller;

import com.nttdata.product.management.controller.mapper.ProductControllerMapper;
import com.nttdata.product.management.exception.FriendlyException;
import com.nttdata.product.management.model.api.CreateProductRequest;
import com.nttdata.product.management.model.api.CreateProductResponse;
import com.nttdata.product.management.model.api.DeactivateProductResponse;
import com.nttdata.product.management.model.api.GetProductsResponse;
import com.nttdata.product.management.model.api.ProductParameter;
import com.nttdata.product.management.model.api.ReplaceProductRequest;
import com.nttdata.product.management.service.ProductService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.webjars.NotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controlador para la gestión de productos.
 * Implementa la interfaz ProductsApi y proporciona endpoints para crear, obtener, actualizar,
 * eliminar y gestionar parámetros de productos.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class ProductController implements ProductsApi {
  private final ProductService productService;
  private final ProductControllerMapper mapper;

  /**
   * Crea un nuevo producto.
   *
   * @param createProductRequest Solicitud para crear un producto.
   * @param exchange             Información del intercambio del servidor web.
   * @return Un Mono que emite una ResponseEntity con la respuesta de creación del producto.
   */
  @Override
  public Mono<ResponseEntity<CreateProductResponse>> createProduct(
      Mono<CreateProductRequest> createProductRequest, ServerWebExchange exchange) {
    return createProductRequest
        .map(mapper::toProductDto)
        .flatMap(productService::create)
        .map(mapper::toCreateProductResponse)
        .onErrorResume(throwable -> new FriendlyException(throwable.getMessage()).buildAsMono())
        .map(response -> ResponseEntity.status(HttpStatus.CREATED)
            .body(response));
  }

  /**
   * Obtiene todos los productos.
   *
   * @param exchange Información del intercambio del servidor web.
   * @return Un Mono que emite una ResponseEntity con un Flux de respuestas de productos.
   */
  @Override
  public Mono<ResponseEntity<Flux<GetProductsResponse>>> getProducts(ServerWebExchange exchange) {
    return productService.getAll()
        .onErrorResume(throwable -> new FriendlyException(throwable.getMessage()).buildAsMono())
        .map(mapper::toGetProductsResponse)
        .collectList()
        .flatMap(list -> {
          if (list.isEmpty()) {
            return Mono.just(ResponseEntity.noContent().build());
          } else {
            return Mono.just(ResponseEntity.ok(Flux.fromIterable(list)));
          }
        });
  }

  /**
   * Reemplaza un producto existente.
   *
   * @param productId             Identificador del producto.
   * @param replaceProductRequest Solicitud para reemplazar el producto.
   * @param exchange              Información del intercambio del servidor web.
   * @return Un Mono que emite una ResponseEntity sin contenido.
   */
  @Override
  public Mono<ResponseEntity<Void>> replaceProduct(
      UUID productId,
      Mono<ReplaceProductRequest> replaceProductRequest,
      ServerWebExchange exchange) {
    return replaceProductRequest
        .map(mapper::toProductDto)
        .flatMap(dto -> productService.update(productId, dto))
        .onErrorResume(throwable -> new FriendlyException(throwable.getMessage()).buildAsMono())
        .thenReturn(ResponseEntity.noContent().build());
  }

  /**
   * Elimina físicamente un producto.
   *
   * @param productId Identificador del producto.
   * @param exchange  Información del intercambio del servidor web.
   * @return Un Mono que emite una ResponseEntity sin contenido.
   */
  @Override
  public Mono<ResponseEntity<Void>> removeProduct(UUID productId, ServerWebExchange exchange) {
    return productService.deletePhysical(productId)
        .thenReturn(ResponseEntity.noContent().build());
  }

  /**
   * Agrega parámetros a un producto.
   *
   * @param productId        Identificador del producto.
   * @param productParameter Parámetros del producto.
   * @param exchange         Información del intercambio del servidor web.
   * @return Un Mono que emite una ResponseEntity sin contenido.
   */
  @Override
  public Mono<ResponseEntity<Void>> addProductParameter(
      UUID productId, Flux<ProductParameter> productParameter, ServerWebExchange exchange) {
    return productParameter
        .map(mapper::toProductParameterDto)
        .collectList()
        .flatMap(parameterDto -> productService.updateParameters(productId, parameterDto)
            .thenReturn(ResponseEntity.noContent().build()));
  }

  /**
   * Desactiva un producto lógicamente.
   *
   * @param productId Identificador del producto.
   * @param exchange  Información del intercambio del servidor web.
   * @return Un Mono que emite una ResponseEntity con la respuesta de desactivación del producto.
   */
  @Override
  public Mono<ResponseEntity<DeactivateProductResponse>> deactivateProduct(
      UUID productId, ServerWebExchange exchange) {
    return productService.deleteLogical(productId)
        .map(mapper::toDeactivateProductResponse)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> new FriendlyException(throwable.getMessage()).buildAsMono());
  }

  /**
   * Obtiene un producto por su identificador.
   *
   * @param productId Identificador del producto.
   * @param exchange  Información del intercambio del servidor web.
   * @return Un Mono que emite una ResponseEntity con la respuesta del producto.
   */
  @Override
  public Mono<ResponseEntity<GetProductsResponse>> getProductById(
      UUID productId, ServerWebExchange exchange) {
    return productService.getProductById(productId)
        .map(mapper::toGetProductsResponse)
        .map(ResponseEntity::ok)
        .doOnError(throwable -> new NotFoundException(throwable.getMessage()));
  }
}