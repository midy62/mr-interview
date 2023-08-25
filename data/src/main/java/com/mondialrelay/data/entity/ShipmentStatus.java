package com.mondialrelay.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShipmentStatus {

    @Id
    @SequenceGenerator(name = "ID_SHIPMENT_STATUS_GEN", sequenceName = "shipment_status_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SHIPMENT_STATUS_GEN")
    private long id;

    @Column(unique = true)
    private String code;

}
