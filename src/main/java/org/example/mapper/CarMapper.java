package org.example.mapper;

import org.example.dto.CarDTO;
import org.example.entity.CarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(target = "id", source = "id")
    CarDTO toDTO(CarEntity carEntity);

    @Mapping(target = "id", ignore = true)
    CarEntity toEntity(CarDTO carDTO);
}
