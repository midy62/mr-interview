package com.mondialrelay.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
public class Parcel {

    @Id
    @SequenceGenerator(name = "ID_PARCEL_GEN", sequenceName = "parcel_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_PARCEL_GEN")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;

    private short sequence;

}
