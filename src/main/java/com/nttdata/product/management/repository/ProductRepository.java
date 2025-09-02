package com.nttdata.product.management.repository;

import com.nttdata.product.management.model.entity.ProductEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, UUID> {
    //Mono<ProductEntity> findByProductId(UUID productId);
    Flux<ProductEntity> findByStatus(Boolean status);
}
