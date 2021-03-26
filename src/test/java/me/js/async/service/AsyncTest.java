package me.js.async.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AsyncTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AsyncTestService asyncTestService;

    @Test
    void void_테스트() throws InterruptedException {

    }

}
