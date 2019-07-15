package com.ning.thread.volatiledemo;

/**
 * @Author JAY
 * @Date 2019/7/12 16:01
 * @Description TODO
 **/
public class VolatileDemo {

    public /*volatile*/ static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {
            int i = 0;
            while (!stop){
                i++;
                //System.out.println 底层是将字符串写入到磁盘，触发了cpu层面的内存屏障，将 store bufferes 中的指令写入到内存。所以，此时，就算不加volatile关键字，也能获取到stop的改变，也一样能够终止循环
                System.out.println("t1 is called.");
            }
            System.out.println("i = " + i);
        });
        threadA.start();
        Thread.sleep(1000);
        stop = true;
    }
}
