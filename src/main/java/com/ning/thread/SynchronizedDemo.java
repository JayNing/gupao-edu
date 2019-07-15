package com.ning.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author JAY
 * @Date 2019/7/11 19:52
 * @Description TODO
 **/
public class SynchronizedDemo implements Runnable{
    int x = 100;

    public synchronized void m1() {
        x = 1000;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("x=" + x);
    }

    public synchronized void m2() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        x = 2000;
    }
    public static void main(String[] args) throws InterruptedException {
        SynchronizedDemo sd = new SynchronizedDemo();
        new Thread(()->sd.m1()).start();
        new Thread(()->sd.m2()).start();
        Thread.sleep(10);
        sd.m2();
        System.out.println("Main x=" + sd.x);
    }
    @Override
    public void run() {
        m1();
    }
}
