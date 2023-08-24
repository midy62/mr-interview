package com.mondialrelay.business.service;

import com.mondialrelay.business.dto.ParcelDto;
import com.mondialrelay.business.mapper.ParcelMapper;
import com.mondialrelay.data.entity.Parcel;
import com.mondialrelay.data.entity.Shipment;
import com.mondialrelay.data.repository.ParcelRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ParcelService {

    private ParcelRepository repository;
    private ParcelMapper mapper;

    ParcelDto createParcel(final ParcelDto dto, final Shipment shipment) {
        final var parcel = mapper.map(dto);
        parcel.setShipment(shipment);
        return mapper.map(repository.save(parcel));
    }

    public ParcelDto updateParcel(final Long parcelId, final ParcelDto updatedParcelDto) {
        final var existingParcel = getParcelById(parcelId);
        existingParcel.setSequence(updatedParcelDto.getSequence());
        return mapper.map(repository.save(existingParcel));
    }

    public void deleteParcel(Long parcelId) {
        final var parcel = getParcelById(parcelId);
        repository.delete(parcel);
    }

    public Parcel getParcelById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parcel not found with id: " + id));
    }
}
