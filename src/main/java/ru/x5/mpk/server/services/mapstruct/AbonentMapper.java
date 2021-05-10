package ru.x5.mpk.server.services.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.x5.mpk.server.entities.Abonent;
import ru.x5.mpk.server.entities.MpkAddress;
import ru.x5.mpk.server.restapi.controllers.abonents.AbonentDto;
import ru.x5.mpk.server.restapi.controllers.mpkaddress.MpkAddressDto;
import ru.x5.mpk.server.services.mapstruct.config.MapstructConfig;

import java.util.List;

@Mapper(config = MapstructConfig.class)
public interface AbonentMapper {
    @Mapping(source = "fullName", target = "fullName")
    AbonentDto mapToDto(Abonent entity);
    List<AbonentDto> mapToDtoList(List<Abonent> entityList);
}
