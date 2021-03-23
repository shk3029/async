package me.js.async.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncService.class);

    @Async
    public void hello(int i) {
        logger.info("heelo I = " +i);
    }

    public void call(int i) {
        hello(i);
    }
}
