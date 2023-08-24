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

    /**
     * Retrieves a collection of all shipments.
     *
     * @return A collection of shipment DTOs.
     */
    @GetMapping
    public Collection<ShipmentDto> findShipments() {
        return shipmentService.findShipments();
    }

    /**
     * Creates a new shipment.
     *
     * @param dto The DTO containing shipment information.
     * @return A ResponseEntity containing the created shipment DTO.
     */
    @PostMapping
    public ResponseEntity<ShipmentDto> createShipment(final @RequestBody ShipmentDto dto) {
        return ResponseEntity.status(CREATED).body(shipmentService.createShipment(dto));
    }

    /**
     * Updates an existing shipment.
     *
     * @param id       The ID of the shipment to update.
     * @param shipment The updated shipment information.
     * @return A ResponseEntity containing the updated shipment DTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ShipmentDto> updateShipment(@PathVariable final Long id, @RequestBody final ShipmentDto shipment) {
        return ResponseEntity.ok(shipmentService.updateShipment(id, shipment));
    }

    /**
     * Deletes a shipment by its ID.
     *
     * @param id The ID of the shipment to delete.
     * @return A ResponseEntity with no content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable final Long id) {
        shipmentService.deleteShipment(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Creates a new parcel for a specific shipment.
     *
     * @param shipmentId The ID of the shipment for which to create the parcel.
     * @param parcel     The DTO containing parcel information.
     * @return A ResponseEntity containing the created parcel DTO.
     */
    @PostMapping("/{shipmentId}/parcels")
    public ResponseEntity<ParcelDto> createParcelForShipment(
            @PathVariable Long shipmentId,
            @RequestBody ParcelDto parcel
    ) {
        final var createdParcel = shipmentService.createParcelForShipment(shipmentId, parcel);
        return ResponseEntity.status(CREATED).body(createdParcel);
    }

}
