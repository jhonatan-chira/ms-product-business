package com.nttdata.product.management.repository;

import com.nttdata.product.management.model.entity.ProductEntity;
import java.util.UUID;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * Repositorio para la gestión de entidades de productos.
 * Extiende ReactiveMongoRepository para proporcionar operaciones reactivas con MongoDB.
 */
public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, UUID> {

  /**
   * Busca productos por su estado.
   *
   * @param status el estado de los productos a buscar (true para activos, false para inactivos)
   * @return un Flux que emite las entidades ProductEntity encontradas
   */
  Flux<ProductEntity> findByStatus(Boolean status);
}