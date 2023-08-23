package com.mondialrelay.business.mapper;

import com.mondialrelay.business.configuration.MapStructConfiguration;
import com.mondialrelay.business.dto.ParcelDto;
import com.mondialrelay.business.dto.ShipmentDto;
import com.mondialrelay.data.entity.Parcel;
import com.mondialrelay.data.entity.Shipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static org.mapstruct.CollectionMappingStrategy.ADDER_PREFERRED;

@Mapper(config = MapStructConfiguration.class, collectionMappingStrategy = ADDER_PREFERRED)
public interface ShipmentMapper {
    @Mapping(target = "parcels", qualifiedByName = "parcelMapper")
    ShipmentDto map(Shipment entity);

    @Named("parcelMapper")
    @Mapping(target = "shipment", ignore = true)
    ParcelDto parcelMapper(Parcel entity);


    @Mapping(target = "parcels", qualifiedByName = "parcelDtoMapper")
    Shipment map(ShipmentDto dto);

    @Named("parcelDtoMapper")
    @Mapping(target = "shipment", ignore = true)
    Parcel parcelDtoMapper(ParcelDto dto);
}
