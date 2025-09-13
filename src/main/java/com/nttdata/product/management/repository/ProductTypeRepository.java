package com.nttdata.product.management.repository;

import com.nttdata.product.management.model.entity.ProductTypeEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * Repositorio para la gestión de entidades de tipo de producto.
 * Extiende ReactiveMongoRepository para proporcionar operaciones reactivas con MongoDB.
 */
public interface ProductTypeRepository extends ReactiveMongoRepository<ProductTypeEntity, Integer> {

  /**
   * Busca una entidad ProductTypeEntity por su ID de tipo de producto.
   *
   * @param productTypeId el ID del tipo de producto a buscar
   * @return un Mono que emite la entidad ProductTypeEntity encontrada, o vacío si no se encuentra
   */
  Mono<ProductTypeEntity> findByProductTypeId(Integer productTypeId);
}