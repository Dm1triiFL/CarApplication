package org.example.mapper;

import org.example.dto.DealershipDTO;
import org.example.dto.CarDTO;
import org.example.entity.DealershipEntity;
import org.example.entity.CarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DealershipMapper {
    DealershipMapper INSTANCE = Mappers.getMapper(DealershipMapper.class);

    DealershipDTO toDTO(DealershipEntity dealershipEntity);

    DealershipEntity toEntity(DealershipDTO dealershipDTO);

    List<CarDTO> carEntitiesToCarDTOs(List<CarEntity> cars);

    List<CarEntity> carDTOsToCarEntities(List<CarDTO> carDTOs);
}
