package com.ning.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author JAY
 * @Date 2019/8/1 19:25
 * @Description 服务端
 **/
public class ServerSocketDemo {

    public static void main(String[] args) {
        ServerSocket serverSocket;
        try {
            //监听
            serverSocket = new ServerSocket(8080);
            //接收客户端连接(阻塞)
            Socket socket = serverSocket.accept();

            //读取客户端发来的输入流
            BufferedReader bos = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //获取socket输出流
            PrintWriter pw = new PrintWriter(socket.getOutputStream());

            //读取控制台的输入信息，并返回给客户端
            BufferedReader bfw = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Client:"+bos.readLine()); //获得输入流的信息
            String response = bfw.readLine();
            //将控制台的输入信息反馈给客户端
            while(!response.equals("bye")){
                pw.println(response);
                pw.flush();
                System.out.println("客户端发来的数据：" + bos.readLine());
                response = bfw.readLine(); //重新获取
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
