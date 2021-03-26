package me.js.async.app;

public class Client {
    public static void main(String[] args) {
        PriceFinder priceFinder = new PriceFinder();
        long start = System.nanoTime();
        System.out.println(priceFinder.findPrices("Mac"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("완료 시간 : " + duration + " msecs");

    }
}
