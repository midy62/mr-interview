package com.mondialrelay.interview.controller;

import com.mondialrelay.business.dto.ShipmentDto;
import com.mondialrelay.business.service.ShipmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.mondialrelay.interview.constant.UrlConstant.SHIPMENTS_PATH;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(SHIPMENTS_PATH)
@AllArgsConstructor
public class ShipmentController {

    private ShipmentService service;

    @GetMapping
    public Collection<ShipmentDto> findShipments() {
        return service.findShipments();
    }

    @PostMapping
    public ResponseEntity<ShipmentDto> createShipment(final @RequestBody ShipmentDto dto) {
        return ResponseEntity.status(CREATED).body(service.createShipment(dto));
    }
}
