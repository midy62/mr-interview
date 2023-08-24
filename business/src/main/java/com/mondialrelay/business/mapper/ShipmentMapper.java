package com.mondialrelay.business.mapper;

import com.mondialrelay.business.configuration.MapStructConfiguration;
import com.mondialrelay.business.dto.ShipmentDto;
import com.mondialrelay.data.entity.Shipment;
import org.mapstruct.Mapper;

import static org.mapstruct.CollectionMappingStrategy.ADDER_PREFERRED;

@Mapper(config = MapStructConfiguration.class, collectionMappingStrategy = ADDER_PREFERRED, uses = ParcelMapper.class)
public interface ShipmentMapper {
    ShipmentDto map(Shipment entity);

    Shipment map(ShipmentDto dto);


}
