package com.mondialrelay.business.service;

import com.mondialrelay.business.dto.ParcelDto;
import com.mondialrelay.business.mapper.ParcelMapper;
import com.mondialrelay.data.entity.Parcel;
import com.mondialrelay.data.entity.Shipment;
import com.mondialrelay.data.repository.ParcelRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
@AllArgsConstructor
public class ParcelService {

    private ParcelRepository repository;
    private ParcelMapper mapper;

    /**
     * Retrieves a collection of parcel associated with the specified shipment ID.
     *
     * @param shipmentId The ID of the shipment for which to retrieve parcels.
     * @return A collection of ParcelDto objects associated with the given shipment ID.
     */
    public Collection<ParcelDto> getParcels(final Long shipmentId) {
        return repository.findAll().stream()
                .filter(parcel -> parcel.getShipment().getId().equals(shipmentId))
                .map(mapper::map)
                .collect(toList());
    }

    /**
     * Creates a new parcel associated with a shipment.
     *
     * @param dto      The DTO containing parcel information.
     * @param shipment The shipment to which the parcel belongs.
     * @return The DTO of the newly created parcel.
     */
    ParcelDto createParcel(final ParcelDto dto, final Shipment shipment) {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("Cannot specify an ID for parcel creation.");
        }
        final var parcel = mapper.map(dto);
        parcel.setShipment(shipment);
        return mapper.map(repository.save(parcel));
    }

    /**
     * Updates an existing parcel's information.
     *
     * @param parcelId         The ID of the parcel to update.
     * @param updatedParcelDto The DTO containing updated parcel information.
     * @return The DTO of the updated parcel.
     */
    public ParcelDto updateParcel(final Long parcelId, final ParcelDto updatedParcelDto) {
        final var existingParcel = getParcelById(parcelId);
        existingParcel.setSequence(updatedParcelDto.getSequence());
        return mapper.map(repository.save(existingParcel));
    }

    /**
     * Deletes a parcel by its ID.
     *
     * @param parcelId The ID of the parcel to delete.
     */
    public void deleteParcel(final Long parcelId) {
        final var parcel = getParcelById(parcelId);
        repository.delete(parcel);
    }

    /**
     * Retrieves a parcel by its ID.
     *
     * @param id The ID of the parcel.
     * @return The parcel with the specified ID.
     * @throws EntityNotFoundException If the parcel is not found.
     */
    public Parcel getParcelById(final Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parcel not found with id: " + id));
    }

    /**
     * Updates a collection of Parcles and returns the updated ParcelDto.
     *
     * @param parcels The collection of ParcelDto objects with updated information.
     * @return A set of ParcelDto objects containing the updated information.
     */
    Set<ParcelDto> updateParcels(final Collection<ParcelDto> parcels) {
        final var parcelsToUpdate = parcels.stream()
                .map(parcelDto -> {
                    final var existingParcel = getParcelById(parcelDto.getId());
                    existingParcel.setSequence(parcelDto.getSequence());
                    return existingParcel;
                })
                .collect(toSet());
        return repository.saveAll(parcelsToUpdate).stream().map(mapper::map).collect(toSet());
    }
}
