package com.nttdata.product.management.service;

import com.nttdata.product.management.model.dto.ProductDto;
import com.nttdata.product.management.model.dto.ProductParameterDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;


public interface ProductService {
    Mono<ProductDto> create(ProductDto productDto);

    Flux<ProductDto> getAll();

    Mono<ProductDto> getProductById(UUID productId);

    Mono<ProductDto> update(UUID productId, ProductDto productDto);

    Mono<ProductDto> updateParameters(UUID productId, List<ProductParameterDto> productParameterDtos);

    Mono<Void> deletePhysical(UUID productId);

    Mono<ProductDto> deleteLogical(UUID productId);


}
