package ru.x5.mpk.server.restapi.controllers.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Message {
    @JsonProperty("message")
    private String message;
}
