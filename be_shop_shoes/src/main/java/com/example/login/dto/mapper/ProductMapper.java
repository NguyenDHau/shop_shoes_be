package com.example.login.dto.mapper;

import com.example.login.dto.product.ProductDetailUpdateDto;
import com.example.login.dto.product.ProductUpdateProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "categoryId", target = "categoryId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "branchId", target = "branchId"),
            @Mapping(source = "productCode", target = "productCode") // Bỏ qua branchId vì không có trong source
    })
    ProductDetailUpdateDto toProductDetailUpdateDto(ProductUpdateProjection projection);
}
