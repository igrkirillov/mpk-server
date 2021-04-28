package ru.x5.mpk.server.services.fias;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Address {
    // "id": "5000000300000450001"
    private String id;
    // "name": "11"
    private String name;
    // "zip": 141985
    private String zip;
    // "type": "дом"
    private String type;
    // "contentType": "building"
    private String contentType;
    // "fullName": "Московская Область, Город Дубна, Улица Мира, 11"
    private String fullName;
    // "guid": "7b5e917b-4f34-4540-832b-58d91fc80604"
    @JsonProperty("guid")
    private String uid;
}
