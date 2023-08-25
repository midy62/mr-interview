package com.mondialrelay.business.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ShipmentStatusDto implements Serializable {

    private Long id;

    private String code;

}
