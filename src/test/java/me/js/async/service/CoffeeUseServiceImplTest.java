package me.js.async.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;

@SpringBootTest
class CoffeeUseServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CoffeeUseService coffeeUseService;

    @Test
    void 동기_블로킹_호출() {
        int americanoPrice = coffeeUseService.getPrice("americano");
        int lattePrice = coffeeUseService.getPrice("latte");

        logger.info("아메리카노 가격 : {}" , americanoPrice);
        logger.info("라떼 가격 : {}" , lattePrice);
        logger.info("최종 가격 : {}" , (americanoPrice + lattePrice));
    }

    @Test
    void 비동기_블록킹_호출() {
        CompletableFuture<Integer> latteFuture = coffeeUseService.getPriceAsync("latte");
        CompletableFuture<Integer> americanoFuture = coffeeUseService.getPriceAsync("americano");
        logger.info("작업이 수행중...");

        int lattePrice = latteFuture.join(); // 블록킹
        int americanoPrice = americanoFuture.join(); // 블록킹
        logger.info("최종 가격을 전달받음 : {}", (lattePrice + americanoPrice));
     }

    // thenAccept -> 데이터를 반환하지 않음
    @Test
    void 비동기_논블록킹_호출_콜백_thenAccept() {
        CompletableFuture<Void> mochaFuture = coffeeUseService
                .getPriceAsync("mocha")
                .thenAccept(p->{
                    logger.info("콜백 : 가격은 {} 원 (아직 데이터를 반환하지 않음)", p);
                });

        logger.info("최종 데이터를 받지 않았지만, 다른 작업을 수행중... (논블록킹)");
        logger.info(String.valueOf(mochaFuture.join())); // 메인 쓰레드를 종료시키지 않기 위해 작성한 임시코드 (운영에서는 필요없음)
    }

    // thenApply() -> 데이터를 반환
    @Test
    void 비동기_논블록킹_호출_콜백_thenApply() {
        CompletableFuture<Void> mochaFuture = coffeeUseService
                .getPriceAsync("mocha")
                .thenApply(p -> {
                    logger.info("같은 쓰레드로 동작");
                    return p + 100;
                })
                .thenAccept(p -> {
                    logger.info("콜백 : 가격은 " + p + "원 (아직 데이터를 반환하지 않음)");
                });


        logger.info("최종 데이터를 받지 않았지만, 다른 작업을 수행중... (논블록킹)");
        logger.info(String.valueOf(mochaFuture.join())); // 메인 쓰레드를 종료시키지 않기 위해 작성한 임시코드 (운영에서는 필요없음);
    }


}