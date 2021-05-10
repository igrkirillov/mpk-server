package ru.x5.mpk.server.restapi.controllers.abonents;

import lombok.Data;

@Data
public class AbonentsCreationParameters {
    private String fullName;
    private String fiasUid;
    private String zip;
}
