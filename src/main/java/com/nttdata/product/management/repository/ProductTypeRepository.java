package com.nttdata.product.management.repository;

import com.nttdata.product.management.model.entity.ProductTypeEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ProductTypeRepository extends ReactiveMongoRepository<ProductTypeEntity, Integer> {
    Mono<ProductTypeEntity> findByProductTypeId(Integer productTypeId);
}
