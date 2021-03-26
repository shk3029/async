package me.js.async.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class AsyncTestService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncTestService.class);

    // @Async를 적용할 때, public을 사용하자
    @Async
    public void onAsync() {
        try {
            Thread.sleep(5000);
            logger.info("onAsync");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onSync() {
        try {
            Thread.sleep(5000);
            logger.info("onSync");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Mono<String> postTest1() {
        logger.info("비동기 호출 1");
        WebClient webClient = WebClient.builder().build();

        Mono<String> stringMono = webClient.post()
                .uri("http://localhost:8081/asyncTest", String.class)
                .retrieve()
                .bodyToMono(String.class);

        return stringMono;
    }

    public Mono<String> postTest2() {
        logger.info("비동기 호출 2");
        WebClient webClient = WebClient.builder().build();

        Mono<String> stringMono = webClient.post()
                .uri("http://localhost:8082/asyncTest", String.class)
                .retrieve()
                .bodyToMono(String.class);
        return stringMono;
    }

}
