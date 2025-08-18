package com.nttdata.product.management.controller;

import com.nttdata.product.management.controller.mapper.ProductControllerMapper;
import com.nttdata.product.management.model.api.CreateProductRequest;
import com.nttdata.product.management.model.api.CreateProductResponse;
import com.nttdata.product.management.model.api.GetProductsResponse;
import com.nttdata.product.management.model.api.ReplaceProductRequest;
import com.nttdata.product.management.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController implements ProductsApi {
    private final ProductService productService;
    private final ProductControllerMapper productControllerMapper;

    @PostMapping
    @Override
    public Mono<ResponseEntity<CreateProductResponse>> createProduct(Mono<CreateProductRequest> createProductRequest, ServerWebExchange exchange) {
        return createProductRequest
                .map(productControllerMapper::toProductDto)
                .flatMap(productService::create)
                .map(productControllerMapper::toCreateProductResponse)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(response));
    }

    @GetMapping
    @Override
    public Mono<ResponseEntity<Flux<GetProductsResponse>>> getProducts(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(
                productService.getAll()
                        .map(productControllerMapper::toGetProductsResponse)
        ));
    }

    @PutMapping("/{productId}")
    @Override
    public Mono<ResponseEntity<Void>> replaceProduct(String productId, Mono<ReplaceProductRequest> replaceProductRequest, ServerWebExchange exchange) {
        return replaceProductRequest
                .map(productControllerMapper::toProductDto)
                .flatMap(dto -> productService.update(productId, dto))
                .thenReturn(ResponseEntity.noContent().build());
    }

    @DeleteMapping("/{productId}")
    @Override
    public Mono<ResponseEntity<Void>> removeProduct(String productId, Mono<ReplaceProductRequest> replaceProductRequest, ServerWebExchange exchange) {
        return productService.delete(productId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
