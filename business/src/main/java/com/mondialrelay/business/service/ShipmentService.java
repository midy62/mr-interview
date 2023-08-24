package com.mondialrelay.business.service;

import com.mondialrelay.business.dto.ParcelDto;
import com.mondialrelay.business.dto.ShipmentDto;
import com.mondialrelay.business.mapper.ShipmentMapper;
import com.mondialrelay.data.entity.Shipment;
import com.mondialrelay.data.repository.ShipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class ShipmentService {

    private ShipmentRepository repository;
    private ParcelService parcelService;
    private ShipmentMapper mapper;

    private Shipment getShipmentById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Shipment not found with id: " + id));
    }

    public Collection<ShipmentDto> findShipments() {
        return repository.findAll().stream().map(mapper::map).collect(toList());
    }

    public ShipmentDto createShipment(final ShipmentDto dto) {
        final var shipment = mapper.map(dto);
        final var entity = repository.save(shipment);
        return mapper.map(entity);
    }

    public ShipmentDto updateShipment(Long id, ShipmentDto updatedShipmentDto) {
        final var existingShipment = getShipmentById(id);
        final var updatedShipment = mapper.map(updatedShipmentDto);
        existingShipment.setTrackingNumber(updatedShipment.getTrackingNumber());
        return mapper.map(repository.save(existingShipment));
    }

    public void deleteShipment(Long id) {
        final var shipment = getShipmentById(id);
        repository.delete(shipment);
    }

    public ParcelDto createParcelForShipment(final Long shipmentId, final ParcelDto dto) {
        final var shipment = getShipmentById(shipmentId);
        return parcelService.createParcel(dto, shipment);
    }
}
