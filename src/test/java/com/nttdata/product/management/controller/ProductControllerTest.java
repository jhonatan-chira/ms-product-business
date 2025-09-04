package com.nttdata.product.management.controller;

import com.nttdata.product.management.controller.mapper.ProductControllerMapperImpl;
import com.nttdata.product.management.exception.FriendlyException;
import com.nttdata.product.management.model.api.ReplaceProductRequest;
import com.nttdata.product.management.model.dto.ProductDto;
import com.nttdata.product.management.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    private ProductServiceImpl productService;
    @Spy
    private ProductControllerMapperImpl mapper;

    @InjectMocks
    private ProductController productController;

    @Test
    @DisplayName("Return no content when product replacement is successful")
    void returnNoContentWhenProductReplacementIsSuccessful() {

        final ReplaceProductRequest replaceProductRequest = new ReplaceProductRequest();
        replaceProductRequest.setName("aaaa");


        var productDtoCaptor = ArgumentCaptor.forClass(ProductDto.class);

        when(productService.update(any(UUID.class), productDtoCaptor.capture()))
                .thenReturn(Mono.just(new ProductDto()));


        StepVerifier.create(
                        productController.replaceProduct(UUID.randomUUID(), Mono.just(replaceProductRequest), null))
                .expectNextMatches(respuesta ->
                        Integer.valueOf(204).equals(respuesta.getStatusCodeValue()))
                .expectComplete()
                .verify();

        Assertions.assertEquals(replaceProductRequest.getName(), productDtoCaptor.getValue().getName());

    }

    @Test
    @DisplayName("Return friendly exception when product replacement fail")
    void returnFriendlyExceptionWhenProductReplacementFail() {

        ReplaceProductRequest replaceProductRequest = new ReplaceProductRequest();
        replaceProductRequest.setProductId(UUID.randomUUID());
        replaceProductRequest.setName("DEF");

        var productDtoCaptor = ArgumentCaptor.forClass(ProductDto.class);

        when(productService.update(any(UUID.class), productDtoCaptor.capture()))
                .thenThrow(new NullPointerException("errrrrorr"));


        StepVerifier.create(
                        productController.replaceProduct(UUID.randomUUID(), Mono.just(replaceProductRequest), null))
                .expectError(FriendlyException.class)
                .verify();


    }


    @Test
    @DisplayName("Return no content when product replacement is successful22")
    void returnNoContentWhenProductReplacementIsSuccessful2() {


        when(productService.getAll())
                .thenReturn(Flux.just(new ProductDto()));


        StepVerifier.create(
                        productController.getProducts(null))
                .expectNextMatches(respuesta ->
                        Integer.valueOf(200).equals(respuesta.getStatusCodeValue()))
                .expectComplete()
                .verify();


    }

    @Test
    @DisplayName("Return no content when product replacement is successful2222")
    void returnNoContentWhenProductReplacementIsSuccessful22() {


        when(productService.getAll())
                .thenReturn(Flux.empty());


        StepVerifier.create(
                        productController.getProducts(null))
                .expectNextMatches(respuesta ->
                        Integer.valueOf(204).equals(respuesta.getStatusCodeValue()))
                .expectComplete()
                .verify();


    }


    @Test
    @DisplayName("Return no content when product replacement is successful222332")
    void returnNoContentWhenProductReplacementIsSuccessful2233() {


        when(productService.getAll())
                .thenThrow(new FriendlyException("No hay data"));


        StepVerifier.create(
                        productController.getProducts(null))
                .expectError(FriendlyException.class)
                .verify();


    }


}
