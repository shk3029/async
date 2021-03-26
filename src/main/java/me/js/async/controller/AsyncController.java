package me.js.async.controller;

import lombok.extern.slf4j.Slf4j;
import me.js.async.service.AsyncTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class AsyncController {

    @Autowired
    private AsyncTestService asyncTestService;

    @GetMapping("/async")
    public String async() {
        asyncTestService.onAsync();

        log.info("async controller");
        log.info("===========");



        return "async";
    }

    @GetMapping("/sync")
    public String sync() {
        asyncTestService.onSync();

        log.info("sync controller");
        log.info("==============");
        return "sync";
    }

    @GetMapping("/asyncTest")
    public String asyncTest() {
        long start = System.nanoTime();
        log.info("===== Start =====");
        Mono<String> stringMonoTest1 = asyncTestService.postTest1();
        Mono<String> stringMonoTest2 = asyncTestService.postTest2();
        log.info("===== End =====");

        StringBuilder builder = new StringBuilder();

        log.info("===== Flux Merge Start =====");
        Flux.merge(stringMonoTest1, stringMonoTest2).subscribe(result -> {
            builder.append(result);
            log.info("== Flux Merge -ing : {} ", result);
        });
        log.info("===== Flux Merge End =====");


        Mono<Void> allMono = Mono.when(stringMonoTest1, stringMonoTest2);
        allMono.block();

        log.info(">>> return value : {} ", builder.toString());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("완료 시간 : " + duration + " msecs");
        return builder.toString();
    }

    @GetMapping("/syncTest")
    public String syncTest() {
        return null;
    }

}






















