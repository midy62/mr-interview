package com.mondialrelay.business.mapper;

import com.mondialrelay.business.configuration.MapStructConfiguration;
import com.mondialrelay.business.dto.ParcelDto;
import com.mondialrelay.data.entity.Parcel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.CollectionMappingStrategy.ADDER_PREFERRED;

@Mapper(config = MapStructConfiguration.class, collectionMappingStrategy = ADDER_PREFERRED)
public interface ParcelMapper {
    @Mapping(target = "shipment", ignore = true)
    ParcelDto map(Parcel entity);

    @Mapping(target = "shipment", ignore = true)
    Parcel map(ParcelDto dto);
}
