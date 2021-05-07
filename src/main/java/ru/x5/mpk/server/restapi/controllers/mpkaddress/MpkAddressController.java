package ru.x5.mpk.server.restapi.controllers.mpkaddress;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.x5.mpk.server.repositories.MpkAddressRepository;
import ru.x5.mpk.server.restapi.controllers.error.Message;
import ru.x5.mpk.server.restapi.controllers.error.MessageAndErrors;
import ru.x5.mpk.server.services.mapstruct.MpkAddressMapper;

import java.util.List;

@RestController
@Api(value = "MpkAddress", tags = {"MpkAddress"}, description = "MpkAddress API")
public class MpkAddressController {

    @Autowired
    private MpkAddressRepository mpkAddressRepository;
    @Autowired
    private MpkAddressMapper mpkAddressMapper;

    @ApiOperation(value = "Получение адресов, удовлетворяющих критериям",
            nickname = "getByCriteria", notes = "", response = MpkAddressDto.class, responseContainer = "List", tags={ "MpkAddress"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = MpkAddressDto.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = MessageAndErrors.class),
            @ApiResponse(code = 422, message = "UnprocessableEntity", response = Message.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = MessageAndErrors.class) })
    @GetMapping(
            value = "/mpkaddresses",
            produces = { "application/json" }
    )
    public List<MpkAddressDto> getList() {
        return mpkAddressMapper.mapToDtoList(mpkAddressRepository.findAll());
    }
}
