package ru.x5.mpk.server.restapi.controllers.greeting;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.x5.mpk.server.restapi.controllers.error.Message;
import ru.x5.mpk.server.restapi.controllers.error.MessageAndErrors;
import ru.x5.mpk.server.services.fias.KladrClient;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@Api(value = "Greeting", tags = {"Greeting"}, description = "Greeting API")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @ApiOperation(value = "Приветствие",
            nickname = "hello", notes = "", response = Greeting.class, tags={"Greeting"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Greeting.class),
            @ApiResponse(code = 400, message = "Bad Request", response = MessageAndErrors.class),
            @ApiResponse(code = 422, message = "UnprocessableEntity", response = Message.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = MessageAndErrors.class) })
    @GetMapping(
            value = "/greeting",
            produces = { "application/json" }
    )
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
