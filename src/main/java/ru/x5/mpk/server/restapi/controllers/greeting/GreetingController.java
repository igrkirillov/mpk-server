package ru.x5.mpk.server.restapi.controllers.greeting;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.x5.mpk.server.entities.TestEntity;
import ru.x5.mpk.server.repositories.TestEntityRepository;
import ru.x5.mpk.server.restapi.controllers.error.Message;
import ru.x5.mpk.server.restapi.controllers.error.MessageAndErrors;
import ru.x5.mpk.server.restapi.controllers.mpkaddress.MpkAddressDto;
import ru.x5.mpk.server.services.fias.KladrClient;

import javax.transaction.Transactional;

@RestController
@Api(value = "Greeting", tags = {"Greeting"}, description = "Greeting API")
public class GreetingController implements InitializingBean {

    @Autowired
    private TestEntityRepository testEntityRepository;
    @Autowired
    private KladrClient kladrClient;

    private static final String template = "Hello, %s! I am %s!";
    private final AtomicLong counter = new AtomicLong();

    @Transactional
    @Override
    public void afterPropertiesSet() throws Exception {
        if (testEntityRepository.findAll().isEmpty()) {
            TestEntity e = new TestEntity();
            e.setUid(UUID.randomUUID().toString());
            e.setField("Dubna");
            testEntityRepository.save(e);
        }
    }

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
        TestEntity e = testEntityRepository.findAll().get(0);
        System.out.println(kladrClient.suggest("Москва"));
        return new Greeting(counter.incrementAndGet(), String.format(template, name, e.getField()));
    }
}
