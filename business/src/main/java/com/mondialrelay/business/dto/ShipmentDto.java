package com.mondialrelay.business.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
public class ShipmentDto implements Serializable {
    private Long id;
    @JsonManagedReference
    private Set<ParcelDto> parcels = new LinkedHashSet<>();
    private long shipmentNumber;

    public void addParcel(final ParcelDto dto) {
        parcels.add(dto);
        dto.setShipment(this);
    }
}
