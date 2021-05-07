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
import ru.x5.mpk.server.restapi.controllers.error.Message;
import ru.x5.mpk.server.restapi.controllers.error.MessageAndErrors;
import ru.x5.mpk.server.restapi.controllers.mpkaddress.MpkAddressDto;
import ru.x5.mpk.server.services.fias.KladrClient;
import ru.x5.mpk.server.services.mapstruct.FiasAddressMapper;
import ru.x5.mpk.server.services.mapstruct.MpkAddressMapper;

import java.util.List;

@RestController
@Api(value = "FiasAddress", tags = {"FiasAddress"}, description = "FiasAddress API")
public class FiasAddressController {

    @Autowired
    private KladrClient kladrClient;
    @Autowired
    private FiasAddressMapper fiasAddressMapper;

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
        return fiasAddressMapper.mapToDtoList(kladrClient.suggest(query).getAddresses());
    }
}
