package ru.x5.mpk.server.restapi.controllers.greeting;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.x5.mpk.server.entities.TestEntity;
import ru.x5.mpk.server.repositories.TestEntityRepository;
import ru.x5.mpk.server.services.fias.KladrClient;

import javax.transaction.Transactional;

@RestController
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

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        TestEntity e = testEntityRepository.findAll().get(0);
        System.out.println(kladrClient.suggest("Москва"));
        return new Greeting(counter.incrementAndGet(), String.format(template, name, e.getField()));
    }
}
