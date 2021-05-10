package ru.x5.mpk.server.restapi.controllers.abonents;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.x5.mpk.server.entities.Abonent;
import ru.x5.mpk.server.entities.MpkAddress;
import ru.x5.mpk.server.repositories.AbonentRepository;
import ru.x5.mpk.server.repositories.MpkAddressRepository;
import ru.x5.mpk.server.restapi.controllers.error.Message;
import ru.x5.mpk.server.restapi.controllers.error.MessageAndErrors;
import ru.x5.mpk.server.restapi.controllers.mpkaddress.MpkAddressDto;
import ru.x5.mpk.server.services.mapstruct.AbonentMapper;
import ru.x5.mpk.server.services.mapstruct.MpkAddressMapper;

import java.util.List;
import java.util.UUID;

@RestController
@Api(value = "Abonents", tags = {"Abonents"}, description = "Abonents API")
public class AbonentsController {

    @Autowired
    private AbonentRepository abonentRepository;
    @Autowired
    private AbonentMapper abonentMapper;

    @ApiOperation(value = "Получение абонентов, удовлетворяющих критериям",
            nickname = "getByCriteria", notes = "", response = AbonentDto.class, responseContainer = "List", tags={ "Abonents"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AbonentDto.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = MessageAndErrors.class),
            @ApiResponse(code = 422, message = "UnprocessableEntity", response = Message.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = MessageAndErrors.class) })
    @GetMapping(
            value = "/abonents",
            produces = { "application/json" }
    )
    public List<AbonentDto> getList() {
        return abonentMapper.mapToDtoList(abonentRepository.findAll());
    }

    @ApiOperation(value = "Создание Abonent", nickname = "create", notes = "", response = AbonentDto.class,
            tags = {"Abonents"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AbonentDto.class),
            @ApiResponse(code = 400, message = "Bad Request", response = MessageAndErrors.class),
            @ApiResponse(code = 422, message = "UnprocessableEntity", response = Message.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = MessageAndErrors.class) })
    @PostMapping(
            value = "/abonents",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    ResponseEntity<AbonentDto> create(@ApiParam(value = "" ,required=true )  @RequestBody AbonentsCreationParameters creationParameters) {
        Abonent entity = new Abonent();
        entity.setUid(UUID.randomUUID().toString());
        entity.setFullName(creationParameters.getFullName());
        entity = abonentRepository.saveAndFlush(entity);
        return ResponseEntity.ok(abonentMapper.mapToDto(entity));
    }

    @ApiOperation(value = "Получение абонентов, привязанных к адресу",
            nickname = "getLinkedToAddress", notes = "", response = AbonentDto.class, responseContainer = "List", tags={ "Abonents"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AbonentDto.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = MessageAndErrors.class),
            @ApiResponse(code = 422, message = "UnprocessableEntity", response = Message.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = MessageAndErrors.class) })
    @GetMapping(
            value = "/abonents",
            produces = { "application/json" }
    )
    public List<AbonentDto> getList(@RequestParam("mpkAddressUid") @ApiParam(value = "mpkAddressUid") String mpkAddressUid) {
        return abonentMapper.mapToDtoList(abonentRepository.findAbonentsBy(mpkAddressUid));
    }
}
