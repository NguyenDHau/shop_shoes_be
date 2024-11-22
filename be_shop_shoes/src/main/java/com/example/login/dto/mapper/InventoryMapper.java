package com.example.login.dto.mapper;

import com.example.login.dto.product.InventoryDetailUpdateDto;
import com.example.login.dto.product.InventoryUpdateProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    @Mapping(source = "sizeId", target = "sizeId")
    @Mapping(source = "colorId", target = "colorId")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "sizeName", target = "sizeName")
    List<InventoryDetailUpdateDto> toInventoryDetailUpdateDto(List<InventoryUpdateProjection> projection);
}
