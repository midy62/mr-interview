package com.mondialrelay.business.service;

import com.mondialrelay.business.dto.ShipmentDto;
import com.mondialrelay.business.mapper.ShipmentMapper;
import com.mondialrelay.data.repository.ShipmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class ShipmentService {

    private ShipmentRepository repository;

    private ShipmentMapper mapper;

    public Collection<ShipmentDto> findShipments() {
        return repository.findAll().stream().map(mapper::map).collect(toList());
    }

    public ShipmentDto createShipment(final ShipmentDto dto) {
        final var shipment = mapper.map(dto);
        final var entity = repository.save(shipment);
        return mapper.map(entity);
    }
}
