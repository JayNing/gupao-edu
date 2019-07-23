package com.ning.thread.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author JAY
 * @Date 2019/7/23 20:18
 * @Description TODO
 **/
public class BlockQueueDemo {

    /**
     * 订单队列
     */
    private static ArrayBlockingQueue<String> orders = new ArrayBlockingQueue<>(5,true);

    private static boolean isRunning = true;

    /**
     *  处理订单(一直监听着阻塞队列)
     * @return
     */
    public static void processOrder(){
        while (isRunning){
            try {
                //阻塞获取订单，处理订单
                String orderId = orders.take();
//                System.out.println("处理----订单：" + orderId);
                Thread.sleep(2000L);
//                System.out.println("处理前 ---- 订单队列的大小：" + orders.size());
                orders.remove(orderId);
                System.out.println("处理后 ---- 订单队列的大小：" + orders.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 新增订单
     * @param orderId
     */
    public static void add(String orderId){
        try {
            //阻塞新增订单
            System.out.println("新增订单：" + orderId);
            orders.put(orderId);
//            System.out.println("新增后--- 订单队列的大小：" + orders.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new Thread(() -> {
            processOrder();
        }).start();
        for (int i = 1; i<=10; i++){
            add("订单" + i);
        }
    }

}
