package com.ning.thread.lockdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author JAY
 * @Date 2019/7/15 10:55
 * @Description TODO
 **/
public class CanditionDemo {

    static List<String> queue = new ArrayList<>();
    private Lock lock;
    private Condition notEmpty;
    private Condition notFull;

    public CanditionDemo(Lock lock, Condition notEmpty, Condition notFull) {
        this.lock = lock;
        this.notEmpty = notEmpty;
        this.notFull = notFull;
    }

    public void put(String value){
        try {
            lock.lock();
            while (queue.size() == 5){
                System.out.println("put队列已满，需等待");
                notFull.await();
            }
            notEmpty.signal();
            System.out.println("入队列数据：" + value);
            queue.add(value);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public String take() {
        String value = null;
        try {
            lock.lock();
            while (queue.size() == 0){
                System.out.println("take队列为空，需等待");
                notEmpty.await();
            }
            value = queue.get(0);
            List<String> newQueue = new ArrayList<>();
            for (int i = 1; i < queue.size(); i++) {
                newQueue.add(queue.get(i));
            }
            queue = newQueue;
            System.out.println("出队列数据：" + value);
            notFull.signal();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return value;
    }

}
