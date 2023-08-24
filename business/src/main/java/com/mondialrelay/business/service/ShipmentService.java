package com.mondialrelay.business.service;

import com.mondialrelay.business.dto.ParcelDto;
import com.mondialrelay.business.dto.ShipmentDto;
import com.mondialrelay.business.mapper.ShipmentMapper;
import com.mondialrelay.data.entity.Parcel;
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

    /**
     * Retrieves a shipment by its ID.
     *
     * @param id The ID of the shipment.
     * @return The shipment with the specified ID.
     * @throws EntityNotFoundException If the shipment is not found.
     */
    private Shipment getShipmentById(final Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Shipment not found with id: " + id));
    }

    /**
     * Retrieves a list of all shipments.
     *
     * @return A collection of shipment DTOs.
     */
    public Collection<ShipmentDto> findShipments() {
        return repository.findAll().stream().map(mapper::map).collect(toList());
    }

    /**
     * Creates a new shipment.
     *
     * @param dto The DTO containing shipment information.
     * @return The DTO of the newly created shipment.
     */
    public ShipmentDto createShipment(final ShipmentDto dto) {
        final var shipment = mapper.map(dto);
        final var entity = repository.save(shipment);
        return mapper.map(entity);
    }

    /**
     * Updates an existing shipment.
     *
     * @param id                 The ID of the shipment to update.
     * @param updatedShipmentDto The DTO containing updated shipment information.
     * @return The DTO of the updated shipment.
     * @throws IllegalArgumentException If any of the parcel in {@code updatedShipmentDto} are not linked to the shipment.
     */
    public ShipmentDto updateShipment(final Long id, final ShipmentDto updatedShipmentDto) {
        final var existingShipment = getShipmentById(id);
        final var updatedShipment = mapper.map(updatedShipmentDto);
        existingShipment.setTrackingNumber(updatedShipment.getTrackingNumber());
        final var dto = mapper.map(repository.save(existingShipment));
        // Validate and update parcels
        validateParcelIdsLinkedToShipment(existingShipment.getParcels(), updatedShipmentDto.getParcels());
        dto.setParcels(parcelService.updateParcels(updatedShipmentDto.getParcels()));
        return dto;
    }

    private void validateParcelIdsLinkedToShipment(final Collection<Parcel> existingParcels, final Collection<ParcelDto> updatedParcels) {
        final var isInvalidParcel = updatedParcels.stream()
                .anyMatch(updatedParcel -> existingParcels.stream()
                        .noneMatch(existingParcel -> existingParcel.getId().equals(updatedParcel.getId())));
        if (isInvalidParcel) {
            throw new IllegalArgumentException("One or more parcels are not linked to the shipment.");
        }
    }

    /**
     * Deletes a shipment by its ID.
     *
     * @param id The ID of the shipment to delete.
     */
    public void deleteShipment(final Long id) {
        final var shipment = getShipmentById(id);
        repository.delete(shipment);
    }

    /**
     * Creates a new parcel for a specific shipment.
     *
     * @param shipmentId The ID of the shipment to which the parcel belongs.
     * @param dto        The DTO containing parcel information.
     * @return The DTO of the newly created parcel.
     */
    public ParcelDto createParcelForShipment(final Long shipmentId, final ParcelDto dto) {
        final var shipment = getShipmentById(shipmentId);
        return parcelService.createParcel(dto, shipment);
    }
}
