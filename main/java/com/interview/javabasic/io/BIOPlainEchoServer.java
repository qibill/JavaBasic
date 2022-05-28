package com.interview.javabasic.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOPlainEchoServer implements PlainEchoServer {

    @Override
    public void serve(int port)throws IOException{
        final ServerSocket socket = new ServerSocket(port);
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        while (true){
            //阻塞直到收到新的客户端链接
            final Socket clientSocket = socket.accept();
            System.out.println("Accepted connection from " + clientSocket);
            //将请求提交给线程池去执行
            executorService.execute(() -> {
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                    while (true){
                        writer.println(reader.readLine());
                        writer.flush();
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
