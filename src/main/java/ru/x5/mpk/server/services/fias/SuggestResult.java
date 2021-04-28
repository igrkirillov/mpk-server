package ru.x5.mpk.server.services.fias;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SuggestResult {
    private SearchContext searchContext;
    @JsonProperty("result")
    private List<Address> addresses;
}
