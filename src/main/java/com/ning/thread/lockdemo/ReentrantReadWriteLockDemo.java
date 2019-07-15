package com.ning.thread.lockdemo;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author JAY
 * @Date 2019/7/15 13:56
 * @Description TODO
 **/
public class ReentrantReadWriteLockDemo {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            try {
                readLock.lock();
                System.out.println("t1读操作。。。。开始");
                Thread.sleep(2000);
                System.out.println("t1读操作。。。。结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                readLock.unlock();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                readLock.lock();
                System.out.println("t2读操作。。。。开始");
                Thread.sleep(2000);
                System.out.println("t2读操作。。。。结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                readLock.unlock();
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                writeLock.lock();
                System.out.println("t3写操作。。。。开始");
                Thread.sleep(2000);
                System.out.println("t3写操作。。。。结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                writeLock.unlock();
            }
        });

        Thread thread4 = new Thread(() -> {
            try {
                writeLock.lock();
                System.out.println("t4写操作。。。。开始");
                Thread.sleep(2000);
                System.out.println("t4写操作。。。。结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                writeLock.unlock();
            }
        });

        /**
         * 读与读
         */
//        thread1.start();
//        thread2.start();

        /**
         * 读与写
         */
//        thread2.start();
//        thread3.start();

        /**
         * 写与写
         */
        thread3.start();
        thread4.start();
    }

}
