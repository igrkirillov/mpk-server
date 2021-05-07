package ru.x5.mpk.server.services.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.x5.mpk.server.entities.MpkAddress;
import ru.x5.mpk.server.restapi.controllers.fiasaddress.FiasAddressDto;
import ru.x5.mpk.server.restapi.controllers.mpkaddress.MpkAddressDto;
import ru.x5.mpk.server.services.fias.Address;
import ru.x5.mpk.server.services.mapstruct.config.MapstructConfig;

import java.util.List;

@Mapper(config = MapstructConfig.class)
public interface FiasAddressMapper {
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "uid", target = "fiasUid")
    FiasAddressDto mapToDto(Address entity);
    List<FiasAddressDto> mapToDtoList(List<Address> entityList);
}
