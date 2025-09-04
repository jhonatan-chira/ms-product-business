package com.nttdata.product.management.service.impl;

import com.nttdata.product.management.exception.FriendlyException;
import com.nttdata.product.management.model.dto.ProductDto;
import com.nttdata.product.management.model.dto.ProductParameterDto;
import com.nttdata.product.management.repository.ProductRepository;
import com.nttdata.product.management.repository.ProductTypeRepository;
import com.nttdata.product.management.service.ProductService;
import com.nttdata.product.management.service.mapper.ProductServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductServiceMapper mapper;
    private final ProductTypeRepository productTypeRepository;

    @Override
    public Mono<ProductDto> create(ProductDto productDto) {
        return productTypeRepository.findByProductTypeId(productDto.getProductType().getProductTypeId())
                .switchIfEmpty(new FriendlyException("ProductType not found with id: " + productDto.getProductType().getProductTypeId()).buildAsMono())
                .map(mapper::toProductTypeDTO)
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

    @Override
    public Flux<ProductDto> getAll() {
        return productRepository.findByStatus(true)
                .map(mapper::toProductDto);
    }

    @Override
    public Mono<ProductDto> getProductById(UUID productId) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new NotFoundException("Product not found with id: " + productId)))
                .map(mapper::toProductDto);
    }

    @Transactional
    @Override
    public Mono<ProductDto> update(UUID productId, ProductDto productDto) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new NotFoundException("Product not found with id: " + productId)))
                .zipWith(
                        productTypeRepository.findByProductTypeId(productDto.getProductType().getProductTypeId())
                                .switchIfEmpty(Mono.error(new NotFoundException("ProductType not found"))),
                        (existingProduct, productType) -> {
                            mapper.toProductEntity(existingProduct, productDto);
                            return existingProduct;
                        })
                .flatMap(productRepository::save)
                .map(mapper::toProductDto);
    }

    @Override
    public Mono<ProductDto> updateParameters(UUID productId, List<ProductParameterDto> productParameterDtos) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new NotFoundException("Product not found with id: " + productId)))
                .map(existingProduct -> {
                    existingProduct.setParameters(mapper.toProductParameterEntities(productParameterDtos));
                    return existingProduct;
                })
                .flatMap(productRepository::save)
                .map(mapper::toProductDto);
    }

    @Override
    public Mono<Void> deletePhysical(UUID productId) {
        return productRepository.deleteById(productId);
    }

    @Override
    public Mono<ProductDto> deleteLogical(UUID productId) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new NotFoundException("Product not found with id: " + productId)))
                .flatMap(product -> {
                    product.setStatus(false);
                    return productRepository.save(product);
                })
                .map(mapper::toProductDto);
    }
}
