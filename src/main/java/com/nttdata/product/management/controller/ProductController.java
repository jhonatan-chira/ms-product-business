package com.nttdata.product.management.controller;

import com.nttdata.product.management.controller.mapper.ProductControllerMapper;
import com.nttdata.product.management.exception.FriendlyException;
import com.nttdata.product.management.model.api.AddProductParameter204Response;
import com.nttdata.product.management.model.api.CreateProductRequest;
import com.nttdata.product.management.model.api.CreateProductResponse;
import com.nttdata.product.management.model.api.DeactivateProductResponse;
import com.nttdata.product.management.model.api.GetProductsResponse;
import com.nttdata.product.management.model.api.ProductParameter;
import com.nttdata.product.management.model.api.ReplaceProductRequest;
import com.nttdata.product.management.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.webjars.NotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ProductController implements ProductsApi {
    private final ProductService productService;
    private final ProductControllerMapper mapper;

    @Override
    public Mono<ResponseEntity<CreateProductResponse>> createProduct(Mono<CreateProductRequest> createProductRequest, ServerWebExchange exchange) {
        return createProductRequest
                .map(mapper::toProductDto)
                .flatMap(productService::create)
                .map(mapper::toCreateProductResponse)
                .doOnError(throwable -> new FriendlyException(throwable.getMessage()).buildAsMono())
                .map(response -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(response));
    }

    @Override
    public Mono<ResponseEntity<Flux<GetProductsResponse>>> getProducts(ServerWebExchange exchange) {
        return productService.getAll()
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

    @Override
    public Mono<ResponseEntity<Void>> replaceProduct(UUID productId, Mono<ReplaceProductRequest> replaceProductRequest, ServerWebExchange exchange) {
        return replaceProductRequest
                .map(mapper::toProductDto)
                .flatMap(dto -> productService.update(productId, dto))
                .thenReturn(ResponseEntity.noContent().build());
    }

    @Override
    public Mono<ResponseEntity<Void>> removeProduct(UUID productId, ServerWebExchange exchange) {
        return productService.deletePhysical(productId)
                .thenReturn(ResponseEntity.noContent().build());
    }

//    @Override
//    public Mono<ResponseEntity<Void>> addProductParameter(UUID productId, Mono<ProductParameter> productParameter, ServerWebExchange exchange) {
//        return productParameter
//                .map(mapper::toProductParameterDto)
//                .flatMap(parameterDto -> productService.updateParameters(productId, parameterDto)
//                        .thenReturn(ResponseEntity.noContent().build()));
//        //.then(Mono.just(ResponseEntity.noContent().build()));
//    }


    @Override
    public Mono<ResponseEntity<AddProductParameter204Response>> addProductParameter(UUID productId, Mono<ProductParameter> productParameter, ServerWebExchange exchange) {
        return ProductsApi.super.addProductParameter(productId, productParameter, exchange);
    }

    @Override
    public Mono<ResponseEntity<DeactivateProductResponse>> deactivateProduct(UUID productId, ServerWebExchange exchange) {
        return productService.deleteLogical(productId)
                .map(mapper::toDeactivateProductResponse)
                .map(ResponseEntity::ok)
                .doOnError(throwable -> new FriendlyException(throwable.getMessage()).buildAsMono());
    }

    @Override
    public Mono<ResponseEntity<GetProductsResponse>> getProductById(UUID productId, ServerWebExchange exchange) {
        return productService.getProductById(productId)
                .map(mapper::toGetProductsResponse)
                .map(ResponseEntity::ok)
                .doOnError(throwable -> new NotFoundException(throwable.getMessage()));
    }
}
