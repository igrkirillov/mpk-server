package ru.x5.mpk.server.restapi.controllers.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MessageAndErrors {

    @JsonProperty("message")
    private String message;

    @JsonProperty("errors")
    private List<String> errors;
}
