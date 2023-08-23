package com.mondialrelay.business.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ParcelDto implements Serializable {
    private Long id;
    @JsonBackReference
    private ShipmentDto shipment;
    private short sequence;
}
