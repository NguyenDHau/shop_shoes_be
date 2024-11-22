package com.example.login.dto.mapper;


import com.example.login.dto.product.ProductColorDetailUpdateDto;
import com.example.login.dto.product.ProductColorUpdateProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductColorMapper {

    ProductColorMapper INSTANCE = Mappers.getMapper(ProductColorMapper.class);

    @Mapping(source = "colorId", target = "colorId")
    @Mapping(source = "fileUrl", target = "fileUrl")
    @Mapping(source = "signature", target = "signature")
    @Mapping(source = "colorName", target = "colorName")
    @Mapping(source = "publicId", target = "publicId")
    @Mapping(source = "fileName", target = "fileName")
    List<ProductColorDetailUpdateDto> toProductColorDetailUpdateDto(List<ProductColorUpdateProjection> projection);
}