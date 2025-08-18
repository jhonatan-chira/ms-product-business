package com.nttdata.product.management.service;

import com.nttdata.product.management.model.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ProductService {
    Mono<ProductDto> create(ProductDto productDto);

    Flux<ProductDto> getAll();

    //Mono<ProductDto> getById(String productId);

    Mono<ProductDto> update(String productId, ProductDto productDto);

    Mono<Void> delete(String productId);
}
