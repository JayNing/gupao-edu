package com.ning.thread;

import java.io.IOException;

/**
 * @Author JAY
 * @Date 2019/7/11 19:58
 * @Description TODO
 **/
public class SynchronizedDemo2 {
    static Integer count=0;
    public static void incr(){
        synchronized (count) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        for(int i=0;i<1000;i++){
            new Thread(()->SynchronizedDemo2.incr()).start();
        }
        Thread.sleep(5000);
        System.out.println("result:"+count);
    }
}
