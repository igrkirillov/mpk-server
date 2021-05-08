package ru.x5.mpk.server.restapi.controllers.fiasaddress;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.x5.mpk.server.entities.MpkAddress;
import ru.x5.mpk.server.repositories.MpkAddressRepository;
import ru.x5.mpk.server.restapi.controllers.error.Message;
import ru.x5.mpk.server.restapi.controllers.error.MessageAndErrors;
import ru.x5.mpk.server.restapi.controllers.mpkaddress.MpkAddressDto;
import ru.x5.mpk.server.services.fias.Address;
import ru.x5.mpk.server.services.fias.KladrClient;
import ru.x5.mpk.server.services.mapstruct.FiasAddressMapper;
import ru.x5.mpk.server.services.mapstruct.MpkAddressMapper;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@Api(value = "FiasAddress", tags = {"FiasAddress"}, description = "FiasAddress API")
public class FiasAddressController {

    @Autowired
    private KladrClient kladrClient;
    @Autowired
    private FiasAddressMapper fiasAddressMapper;
    @Autowired
    private MpkAddressRepository mpkAddressRepository;

    @ApiOperation(value = "Поиск fias-адресов, удовлетворяющих полнотекстовому запросу",
            nickname = "getByCriteria", notes = "", response = FiasAddressDto.class, responseContainer = "List", tags={ "FiasAddress"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = FiasAddressDto.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = MessageAndErrors.class),
            @ApiResponse(code = 422, message = "UnprocessableEntity", response = Message.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = MessageAndErrors.class) })
    @GetMapping(
            value = "/fiasaddresses",
            produces = { "application/json" }
    )
    public List<FiasAddressDto> getList(
            @RequestParam("query") @ApiParam(value = "Пользовательский текст запроса", defaultValue = "Москва")
            String query) {
        List<Address> addresses = kladrClient.suggest(query).getAddresses()
                .stream()
                .filter(a -> a.getContentType().equals(KladrClient.CONTENT_TYPE_BUILDING))
                .collect(Collectors.toList());
        List<FiasAddressDto> fiasAddressDtoList = fiasAddressMapper.mapToDtoList(addresses);
        if (!fiasAddressDtoList.isEmpty()) {
            List<MpkAddress> mpkAddresses = mpkAddressRepository.findByFiasUidList(fiasAddressDtoList.stream()
                    .map(FiasAddressDto::getFiasUid).collect(Collectors.toList()));
            // кэш для производительности
            Map<String, FiasAddressDto> fiasAddressesMap = fiasAddressDtoList.stream()
                    .collect(Collectors.toMap(FiasAddressDto::getFiasUid, Function.identity()));
            mpkAddresses.forEach(mpk -> {
                FiasAddressDto dto = fiasAddressesMap.get(mpk.getFiasUid());
                if (dto != null) {
                    dto.setMpkUid(mpk.getUid());
                }
            });
        }
        return fiasAddressDtoList;
    }
}
