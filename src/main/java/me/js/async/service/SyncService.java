package me.js.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class SyncService {



    public String postTest1() {

        RestTemplate restTemplate = new RestTemplate();
        return null;
    }

    public String postTest2() {
        return null;
    }

}
