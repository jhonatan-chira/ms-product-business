package com.nttdata.product.management.repository;

import com.nttdata.product.management.model.api.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

}
