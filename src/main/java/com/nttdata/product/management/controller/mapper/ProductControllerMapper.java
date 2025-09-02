package com.nttdata.product.management.controller.mapper;

import com.nttdata.product.management.model.api.CreateProductRequest;
import com.nttdata.product.management.model.api.CreateProductResponse;
import com.nttdata.product.management.model.api.DeactivateProductResponse;
import com.nttdata.product.management.model.api.GetProductsResponse;
import com.nttdata.product.management.model.api.ProductParameter;
import com.nttdata.product.management.model.api.ProductType;
import com.nttdata.product.management.model.api.ReplaceProductRequest;
import com.nttdata.product.management.model.dto.ProductDto;
import com.nttdata.product.management.model.dto.ProductParameterDto;
import com.nttdata.product.management.model.dto.ProductTypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {java.time.ZoneOffset.class,
                java.time.OffsetDateTime.class}
)
public interface ProductControllerMapper {

    CreateProductResponse toCreateProductResponse(ProductDto productDto);

    ProductDto toProductDto(CreateProductRequest createProductRequest);

    ProductDto toProductDto(ReplaceProductRequest request);

    GetProductsResponse toGetProductsResponse(ProductDto productDto);

    ProductTypeDto toDto(ProductType productType);

    ProductType toEntity(ProductTypeDto productTypeDTO);

    @Mapping(target = "message", expression = "java(\"Producto desactivado exitosamente\")")
    DeactivateProductResponse toDeactivateProductResponse(ProductDto productDto);

    ProductParameterDto toProductParameterDto(ProductParameter productParameter);
}
