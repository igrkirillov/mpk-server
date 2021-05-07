package ru.x5.mpk.server.restapi.controllers.mpkaddress;

import lombok.Data;

@Data
public class MpkAddressDto {
    private String fullName;
    private String fiasUid;
    private String zip;
}
