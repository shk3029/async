package me.js.async;

import me.js.async.domain.Shop;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncMain {
    public static void main(String[] args) throws InterruptedException {
        Shop shop = new Shop("Jay Shop");

        Future<Double> futurePrice = shop.getAsyncPrice("Jack's Mac");

        doSomethingElse(1);
        doSomethingElse(2);
        doSomethingElse(3);

        try {
            System.out.println("가격은 : " + futurePrice.get());
        } catch (ExecutionException e) {
            System.out.println(e.getCause());
        }

        doSomethingElse(4);

    }

    private static void doSomethingElse(int num) throws InterruptedException {
        System.out.println("아무일... {" + num+"}");
        Thread.sleep(1000);
    }
}
