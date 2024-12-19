package org.example.service;

import org.example.dto.DealershipDTO;
import org.example.entity.DealershipEntity;
import org.example.mapper.CarMapper;
import org.example.repository.DealershipRepository;

import java.util.List;
import java.util.stream.Collectors;

public class DealershipService {

    private final DealershipRepository dealershipRepository;
    private final CarMapper carMapper;

    public DealershipService(DealershipRepository dealershipRepository, CarMapper carMapper) {
        this.dealershipRepository = dealershipRepository;
        this.carMapper = carMapper;
    }

    public List<DealershipDTO> getAllDealerships() {
        List<DealershipEntity> dealershipEntities = dealershipRepository.findAll();
        return dealershipEntities.stream()
                .map(carMapper::dealershipEntityToDealershipDTO)
                .collect(Collectors.toList());
    }

}
