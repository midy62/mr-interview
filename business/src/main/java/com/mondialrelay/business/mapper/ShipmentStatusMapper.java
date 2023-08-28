package com.mondialrelay.business.mapper;

import com.mondialrelay.business.configuration.MapStructConfiguration;
import com.mondialrelay.business.dto.ShipmentStatusDto;
import com.mondialrelay.data.entity.ShipmentStatus;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapStructConfiguration.class)
public interface ShipmentStatusMapper {

    ShipmentStatusDto map(ShipmentStatus entity);

    List<ShipmentStatusDto> map(List<ShipmentStatus> entities);
}
