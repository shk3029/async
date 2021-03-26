package me.js.async.app;

import me.js.async.domain.Shop;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PriceFinder {
    private final List<Shop> shops = Arrays.asList(
            new Shop("Copongdfdf"),
            new Shop("SSGasdf"),
            new Shop("11th asdfStreet"),
            new Shop("Fbaasdfasdfy"));

    public List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> shop.getName() + " " + shop.getPrice(product))
                .collect(Collectors.toList());
    }



}
