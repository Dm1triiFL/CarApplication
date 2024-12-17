package org.example.mapper;

import org.example.dto.CarModelDTO;
import org.example.entity.CarModelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarModelMapper {
    CarModelMapper INSTANCE = Mappers.getMapper(CarModelMapper.class);

    CarModelDTO toDTO(CarModelEntity carModelEntity);
    CarModelEntity toEntity(CarModelDTO carModelDTO);
}
