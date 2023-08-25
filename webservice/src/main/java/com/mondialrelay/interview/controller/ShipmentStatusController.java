package com.mondialrelay.interview.controller;


import com.mondialrelay.business.dto.ShipmentStatusDto;
import com.mondialrelay.business.service.ShipmentStatusService;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

import static com.mondialrelay.interview.constant.UrlConstant.SHIPMENT_STATUSES_PATH;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(SHIPMENT_STATUSES_PATH)
@AllArgsConstructor
public class ShipmentStatusController {

    private ShipmentStatusService shipmentStatusService;

    /**
     * Retrieves a shipment's status by its code if provided,
     * otherwise retrieves all shipment's statuses.
     *
     * @param code shipment's status code
     * @return A shipment's status or a list of shipment's statuses
     */
    @GetMapping
    public ResponseEntity<Collection<ShipmentStatusDto>> retrieveShipmentStatus(
            @RequestParam(required = false) final String code) {
        final Collection<ShipmentStatusDto> result;
        if (StringUtils.isNotEmpty(code)) {
            result = Collections.singleton(shipmentStatusService.getShipmentStatus(code));
        } else {
            result = shipmentStatusService.getAllShipmentStatuses();
        }
        return ResponseEntity.status(OK).body(result);
    }
}
