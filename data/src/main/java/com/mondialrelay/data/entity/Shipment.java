package com.mondialrelay.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Shipment {

    @Id
    @SequenceGenerator(name = "ID_SHIPMENT_GEN", sequenceName = "shipment_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SHIPMENT_GEN")
    private Long id;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Parcel> parcels = new LinkedHashSet<>();

    private long trackingNumber;

    public void addParcel(final Parcel parcel) {
        parcels.add(parcel);
        parcel.setShipment(this);
    }

}
