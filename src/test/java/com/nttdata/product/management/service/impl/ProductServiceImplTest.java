package com.nttdata.product.management.service.impl;

import MockBundle.ProductServiceImplMockBundle;
import com.nttdata.product.management.model.dto.ProductDto;
import com.nttdata.product.management.model.entity.ProductEntity;
import com.nttdata.product.management.model.entity.ProductTypeEntity;
import com.nttdata.product.management.repository.ProductRepository;
import com.nttdata.product.management.repository.ProductTypeRepository;
import com.nttdata.product.management.service.mapper.ProductServiceMapperImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Spy
    private ProductServiceMapperImpl mapper;
    @Mock
    private ProductTypeRepository productTypeRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @SneakyThrows(IOException.class)
    @Test
    @DisplayName("Should create product successfully when product type exists")
    void shouldCreateProductSuccessfullyWhenProductTypeExists() {


        var productDto = ProductServiceImplMockBundle.getProductDtoModel4();
        var productEntity = mapper.toProductEntity(productDto);

        when(productTypeRepository.findByProductTypeId(anyInt()))
                .thenReturn(Mono.just(new ProductTypeEntity()));

        when(productRepository.save(any(ProductEntity.class)))
                .thenReturn(Mono.just(productEntity));


        StepVerifier.create(
                        productService.create(productDto))
                .expectNextMatches(respuesta -> respuesta.getName().equals(productDto.getName()))
                .expectComplete()
                .verify();
    }

    @SneakyThrows(IOException.class)
    @Test
    @DisplayName("Should throw FriendlyException when product type does not exist")
    void shouldThrowFriendlyExceptionWhenProductTypeDoesNotExist() {


        var productDto = ProductServiceImplMockBundle.getProductDtoModel5();
        var productEntity = mapper.toProductEntity(productDto);

        when(productTypeRepository.findByProductTypeId(anyInt()))
                .thenReturn(Mono.empty());

        StepVerifier.create(productService.create(productDto))
                .expectError()
                .verify();
    }

    @SneakyThrows(IOException.class)
    @Test
    @DisplayName("Should generate new UUID when productId is null before saving")
    void shouldGenerateNewUUIDWhenProductIdIsNullBeforeSaving() {


        var productDto = ProductServiceImplMockBundle.getProductDtoModel6();

        var productEntityWithoutId = mapper.toProductEntity(productDto);
        productEntityWithoutId.setProductId(null);

        when(productTypeRepository.findByProductTypeId(anyInt()))
                .thenReturn(Mono.just(new ProductTypeEntity()));

        var entityCaptor = ArgumentCaptor.forClass(ProductEntity.class);

        when(productRepository.save(entityCaptor.capture()))
                .thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));


        StepVerifier.create(
                        productService.create(productDto))
                .expectNextMatches(respuesta -> respuesta.getName().equals(productDto.getName()))
                .expectComplete()
                .verify();

        ProductEntity savedEntity = entityCaptor.getValue();
        assertNotNull(savedEntity.getProductId(), "ProductId should have been generated");
    }


    @SneakyThrows(IOException.class)
    @Test
    @DisplayName("Should update product successfully when product and product type exist")
    void shouldUpdateProductSuccessfullyWhenProductAndProductTypeExist() {


        var productDto = ProductServiceImplMockBundle.getProductDtoModel1();
        var productEntity = mapper.toProductEntity(productDto);

        var productDtoCaptor = ArgumentCaptor.forClass(ProductDto.class);

        when(productRepository.findById(any(UUID.class)))
                .thenReturn(Mono.just(productEntity));

        when(productTypeRepository.findByProductTypeId(anyInt()))
                .thenReturn(Mono.just(new ProductTypeEntity()));

        when(productRepository.save(any(ProductEntity.class)))
                .thenReturn(Mono.just(productEntity));


        StepVerifier.create(
                        productService.update(UUID.randomUUID(), productDto))
                .expectNextMatches(respuesta -> respuesta.getName().equals(productDto.getName()))
                .expectComplete()
                .verify();
    }

    @SneakyThrows(IOException.class)
    @Test
    @DisplayName("Should throw NotFoundException when product does not exist")
    void shouldThrowNotFoundExceptionWhenProductDoesNotExist() {

        var productDto = ProductServiceImplMockBundle.getProductDtoModel2();

        when(productRepository.findById(any(UUID.class)))
                .thenReturn(Mono.empty());

        when(productTypeRepository.findByProductTypeId(anyInt()))
                .thenReturn(Mono.empty());

        StepVerifier.create(
                        productService.update(UUID.randomUUID(), productDto))
                .expectError()
                .verify();
    }

    @SneakyThrows(IOException.class)
    @Test
    @DisplayName("Should throw NotFoundException when product type does not exist")
    void shouldThrowNotFoundExceptionWhenProductTypeDoesNotExist() {

        var productDto = ProductServiceImplMockBundle.getProductDtoModel3();
        var productEntity = mapper.toProductEntity(productDto);

        when(productRepository.findById(any(UUID.class)))
                .thenReturn(Mono.just(productEntity));

        when(productTypeRepository.findByProductTypeId(anyInt()))
                .thenReturn(Mono.empty());

        StepVerifier.create(
                        productService.update(UUID.randomUUID(), productDto))
                .expectError()
                .verify();
    }


}
