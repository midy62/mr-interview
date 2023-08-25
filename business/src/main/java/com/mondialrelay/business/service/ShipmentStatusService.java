package com.mondialrelay.business.service;

import com.mondialrelay.business.dto.ShipmentStatusDto;
import com.mondialrelay.business.mapper.ShipmentStatusMapper;
import com.mondialrelay.data.repository.ShipmentStatusRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Shipment status service.
 */
@Service
@AllArgsConstructor
public class ShipmentStatusService {

    private final Logger LOG = LoggerFactory.getLogger(ShipmentStatusService.class);

    private ShipmentStatusRepository shipmentStatusRepository;

    private ShipmentStatusMapper shipmentStatusMapper;

    /**
     * Get all available shipment's statuses.
     * @return list of shipment's statuses.
     */
    public Collection<ShipmentStatusDto> getAllShipmentStatuses(){
        return getShipmentStatusesMap().values();
    }

    /**
     * Get a shipment status by code.
     * @param code shipment status code.
     * @return shipment status.
     */
    public ShipmentStatusDto getShipmentStatus(final String code){
        return getShipmentStatusesMap().get(code);
    }

    @Cacheable(value = "shipmentStatuses", key = "#root.methodName")
    private Map<String, ShipmentStatusDto> getShipmentStatusesMap(){
        LOG.debug("Retrieving all shipment statuses as a list and put it in cache.");
        final var result = new HashMap<String, ShipmentStatusDto>();
        final var statuses = shipmentStatusMapper.map(shipmentStatusRepository.findAll());
        for (final var status : statuses){
            result.put(status.getCode(), status);
        }
        return result;
    }
}
