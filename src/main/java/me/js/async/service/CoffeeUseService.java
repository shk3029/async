package me.js.async.service;

import java.util.concurrent.CompletableFuture;

public interface CoffeeUseService {

    int getPrice(String name); // 동기

    CompletableFuture<Integer> getPriceAsync(String name); // 비동기
    CompletableFuture<Integer> getDiscountPriceAsync(Integer price); // 비동기
}
