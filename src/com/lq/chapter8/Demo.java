package com.lq.chapter8;


import java.util.List;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        PrimeThread primeThread1 = new PrimeThread(2, 100) {
            @Override
            public void callback(List<Integer> foundPrimeList) {
                System.out.println("回调：primeThread1找到的质数个数: " + this.getPrimeCount());

            }
        };
        PrimeThread primeThread2 = new PrimeThread(2, 100) {
            @Override
            public void callback(List<Integer> foundPrimeList) {
                System.out.println("回调：primeThread2找到的质数个数: " + this.getPrimeCount());
            }
        };
        primeThread1.start();
        primeThread2.start();

        /*
         * 必须对子线程加锁
         */
        synchronized (primeThread1){
            primeThread1.wait();
            System.out.println("主线程等待子线程结果：primeThread1找到的质数个数: " + primeThread1.getPrimeCount());
        }

    }
}
