package com.nttdata.product.management.service.mapper;

import com.nttdata.product.management.model.dto.ProductDto;
import com.nttdata.product.management.model.dto.ProductParameterDto;
import com.nttdata.product.management.model.dto.ProductParameterMovementDto;
import com.nttdata.product.management.model.dto.ProductTypeDto;
import com.nttdata.product.management.model.entity.ProductEntity;
import com.nttdata.product.management.model.entity.ProductParameterEntity;
import com.nttdata.product.management.model.entity.ProductParameterMovementEntity;
import com.nttdata.product.management.model.entity.ProductTypeEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {java.time.ZoneOffset.class,
                java.time.OffsetDateTime.class}
)
public interface ProductServiceMapper {
    ProductEntity toProductEntity(ProductDto productDto);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name", source = "productDto.name")
    @Mapping(target = "status", source = "productDto.status")
    @Mapping(target = "parameters", source = "productDto.parameters")
    @Mapping(target = "productType", source = "productDto.productType")
    ProductEntity toProductEntity(@MappingTarget ProductEntity productEntity, ProductDto productDto);

    ProductTypeEntity toProductTypeEntity(ProductTypeDto productTypeDTO);

    ProductTypeDto toProductTypeDTO(ProductTypeEntity productTypeEntity);

    ProductDto toProductDto(ProductEntity productEntity);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "productDto.productType", source = "productTypeDTO")
    ProductDto toProductDto(ProductTypeDto productTypeDTO, @MappingTarget ProductDto productDto);

    // ProductParameter
//    ProductParameterDto toProductParameterDto(ProductParameterEntity entity);
//
//    ProductParameterEntity toProductParameterEntity(ProductParameterDto dto);
//
//    // ProductParameterMovement
//    ProductParameterMovementDto toProductParameterMovementDto(ProductParameterMovementEntity entity);
//
//    ProductParameterMovementEntity toProductParameterMovementEntity(ProductParameterMovementDto dto);


    List<ProductParameterEntity> toProductParameterEntities(List<ProductParameterDto> dto);

    //Usado internamente por mapStruct cuando encuentra listas anidadas
    List<ProductParameterMovementEntity> toProductParameterMovementEntities(List<ProductParameterMovementDto> dto);
}
