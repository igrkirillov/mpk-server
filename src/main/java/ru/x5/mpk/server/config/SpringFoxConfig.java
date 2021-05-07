package ru.x5.mpk.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(mpkApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.x5.mpk.server.restapi.controllers"))
                .paths(PathSelectors.any())
                .build();
    }

    private static ApiInfo mpkApiInfo() {
        return new ApiInfo("MPK Rest Api", "MPK Server", "1.0", "",
                ApiInfo.DEFAULT_CONTACT, "", "", Collections.emptyList());
    }
}
