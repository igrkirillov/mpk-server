package ru.x5.mpk.server.services.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.x5.mpk.server.entities.MpkAddress;
import ru.x5.mpk.server.restapi.controllers.mpkaddress.MpkAddressDto;
import ru.x5.mpk.server.services.mapstruct.config.MapstructConfig;

import java.util.List;

@Mapper(config = MapstructConfig.class)
public interface MpkAddressMapper {
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "fiasUid", target = "fiasUid")
    @Mapping(source = "zip", target = "zip")
    MpkAddressDto mapToDto(MpkAddress entity);
    List<MpkAddressDto> mapToDtoList(List<MpkAddress> entityList);
}
