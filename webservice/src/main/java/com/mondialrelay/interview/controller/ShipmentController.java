package com.mondialrelay.interview.controller;

import com.mondialrelay.business.dto.ParcelDto;
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

    private ShipmentService shipmentService;

    @GetMapping
    public Collection<ShipmentDto> findShipments() {
        return shipmentService.findShipments();
    }

    @PostMapping
    public ResponseEntity<ShipmentDto> createShipment(final @RequestBody ShipmentDto dto) {
        return ResponseEntity.status(CREATED).body(shipmentService.createShipment(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShipmentDto> updateShipment(@PathVariable final Long id, @RequestBody final ShipmentDto shipment) {
        return ResponseEntity.ok(shipmentService.updateShipment(id, shipment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable final Long id) {
        shipmentService.deleteShipment(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{shipmentId}/parcels")
    public ResponseEntity<ParcelDto> createParcelForShipment(
            @PathVariable Long shipmentId,
            @RequestBody ParcelDto parcel
    ) {
        final var createdParcel = shipmentService.createParcelForShipment(shipmentId, parcel);
        return ResponseEntity.status(CREATED).body(createdParcel);
    }

}
