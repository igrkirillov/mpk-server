package ru.x5.mpk.server.services.fias;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "kladr", url = "${kladr.url}")
public interface KladrClient {

    @RequestMapping(method = RequestMethod.GET, value = "?oneString=1&withParent=0", produces = "application/json")
    SuggestResult suggest(@RequestParam(name = "query") String query);
}
