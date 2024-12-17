package org.example.entity;

import org.example.dto.CarDTO;
import org.example.dto.CarModelDTO;
import org.example.dto.DealershipDTO;
import org.example.entity.CarEntity;
import org.example.entity.CarModelEntity;
import org.example.entity.DealerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    // DTO -> Entity
    CarEntity toEntity(CarDTO carDTO);
    CarModelEntity toEntity(CarModelDTO carModelDTO);
    DealerEntity toEntity(DealershipDTO dealershipDTO);

    // Entity -> DTO
    CarDTO toDTO(CarEntity carEntity);
    CarModelDTO toDTO(CarModelEntity carModelEntity);
    DealershipDTO toDTO(DealerEntity dealerEntity);
}
