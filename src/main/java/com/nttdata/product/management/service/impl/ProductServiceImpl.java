package com.nttdata.product.management.service.impl;

import com.nttdata.product.management.controller.mapper.ProductControllerMapper;
import com.nttdata.product.management.model.api.Product;
import com.nttdata.product.management.model.dto.ProductDto;
import com.nttdata.product.management.repository.ProductRepository;
import com.nttdata.product.management.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductControllerMapper mapper;

    @Override
    public Mono<ProductDto> create(ProductDto productDto) {
        Product product = mapper.toEntity(productDto);
        return productRepository.save(product)
                .map(mapper::toDto);
    }

    @Override
    public Flux<ProductDto> getAll() {
        return productRepository.findAll()
                .map(mapper::toDto);
    }

    @Override
    public Mono<ProductDto> update(String productId, ProductDto productDto) {
        return productRepository.findById(productId)
                .flatMap(existingProduct -> {
                    existingProduct.setName(productDto.getName());
                    existingProduct.setProductType(mapper.toEntity(productDto.getProductType()));
                    return productRepository.save(existingProduct);
                })
                .map(mapper::toDto);
    }

    @Override
    public Mono<Void> delete(String productId) {
        return productRepository.deleteById(productId);
    }
}
