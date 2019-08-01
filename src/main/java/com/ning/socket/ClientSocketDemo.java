package com.ning.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Author JAY
 * @Date 2019/8/1 19:36
 * @Description 客户端
 **/
public class ClientSocketDemo {

    public static void main(String[] args) {
        try {
            //客户端建立连接
            Socket socket = new Socket("127.0.0.1",8080);
            //在当前连接上写入输入流
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            //获取控制台输入的信息
            BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
            //获取到服务端返回的response
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //控制台输入的msg
            String msg = bfr.readLine();
            //将msg写入连接里发送到服务端
            while(!msg.equals("bye")){
                pw.println(msg);
                System.out.println("服务端发来反馈：" + in.readLine());
                msg = bfr.readLine(); //重新获取
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
