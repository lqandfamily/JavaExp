package com.lq.chapter8;

import java.util.ArrayList;
import java.util.List;

/**
 * 多线程寻找质数
 */
public abstract class PrimeThread extends Thread {
    //起始位置
    private int start;
    //结束位置
    private int end;
    private List<Integer> foundPrimeList;

    public PrimeThread(int start, int end) {
        this.start = start;
        this.end = end;
        foundPrimeList = new ArrayList<>(10);
    }


    @Override
    public void run() {
        /*
         * 必须对子线程加锁
         */
        synchronized (this) {


            findPrime();

            //回调
            callback(foundPrimeList);

            //唤醒主线程
            notify();
        }
    }

    /**
     * 寻找质数
     */
    private void findPrime() {
        if (start < 0 || start > end) {
            return;
        }
        for (int i = 2; i < 8; i++) {
            int j;
            for (j = 2; j <= i; j++) {
                if (i % j == 0) {
                    break;
                }
            }
            //质数
            if (j == i) {
                foundPrimeList.add(i);
                System.out.println(this.getName() + "**** " + j);
            }
        }

    }

    /**
     * 获取所有找到的找到的质数
     *
     * @return 质数List
     */
    public List<Integer> getPrimeNum() {
        return foundPrimeList;
    }

    /**
     * 获取质数的个数
     *
     * @return 质数的个数
     */
    public int getPrimeCount() {
        return foundPrimeList.size();
    }

    public abstract void callback(List<Integer> foundPrimeList);

}
