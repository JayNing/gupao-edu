package com.ning.thread.lockdemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author JAY
 * @Date 2019/7/15 13:13
 * @Description TODO
 **/
public class Demo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition notEmpty = lock.newCondition();
        Condition notFull = lock.newCondition();

        for (int i = 1; i<= 10;i++){
            int finalI = i;
            new Thread(() -> {
                CanditionDemo canditionDemo = new CanditionDemo(lock,notEmpty,notFull);
                canditionDemo.put("" + finalI);
            },"t" + i).start();
        }

        for (int i = 1; i<= 10;i++){
            new Thread(() -> {
                CanditionDemo canditionDemo = new CanditionDemo(lock,notEmpty,notFull);
                canditionDemo.take();
            },"t" + i).start();
        }
    }
}
