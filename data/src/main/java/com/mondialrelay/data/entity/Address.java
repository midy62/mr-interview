package com.mondialrelay.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @SequenceGenerator(name = "ID_ADDRESS_GEN", sequenceName = "address_seq", allocationSize = 1)
    private Long id;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    private String postalCode;

    private String city;

    private String country;

}
