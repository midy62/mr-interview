package com.mondialrelay.business.service;

import com.mondialrelay.business.dto.ShipmentStatusDto;
import com.mondialrelay.business.mapper.ShipmentStatusMapper;
import com.mondialrelay.data.repository.ShipmentStatusRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * Shipment status service.
 */
@Service
@AllArgsConstructor
@Slf4j
public class ShipmentStatusService {

    private ShipmentStatusRepository repository;
    private ShipmentStatusMapper mapper;

    /**
     * Get all available shipment's statuses.
     *
     * @return list of shipment's statuses.
     */
    public Collection<ShipmentStatusDto> getAllShipmentStatuses() {
        return getShipmentStatusesMap().values();
    }

    /**
     * Get a shipment status by code.
     *
     * @param code shipment status code.
     * @return shipment status.
     */
    public ShipmentStatusDto getShipmentStatus(final String code) {
        return getShipmentStatusesMap().get(code);
    }

    /**
     * Retrieves shipment statuses as a map and caches the result.
     *
     * @return A map containing shipment statuses with their codes as keys.
     */
    @Cacheable(value = "shipmentStatuses", key = "#root.methodName")
    private Map<String, ShipmentStatusDto> getShipmentStatusesMap() {
        log.debug("Retrieving all shipment statuses as a list and put it in cache.");
        final var result = new HashMap<String, ShipmentStatusDto>();
        final var statuses = mapper.map(repository.findAll());
        for (final var status : statuses) {
            result.put(status.getCode(), status);
        }
        return result;

    }
}
