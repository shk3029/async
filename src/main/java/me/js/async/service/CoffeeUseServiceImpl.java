package me.js.async.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.js.async.repository.CoffeeRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoffeeUseServiceImpl implements CoffeeUseService {

    private final CoffeeRepository coffeeRepository;

    Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Override
    public int getPrice(String name) {
        log.info("동기 조회");
        return coffeeRepository.getPriceByName(name);
    }

    @Override
    public CompletableFuture<Integer> getPriceAsync(String name) {
        log.info("비동기 조회");

        return CompletableFuture.supplyAsync(()-> {
            log.info("Thread Name : {}", Thread.currentThread().getName());
            log.info("SupplyAsync");
            return coffeeRepository.getPriceByName(name);
        }, executor);
    }

    @Override
    public CompletableFuture<Integer> getDiscountPriceAsync(Integer price) {
        return null;
    }
}









