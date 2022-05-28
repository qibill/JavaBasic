package com.interview.javabasic.http;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP {

	// 客户端
	@Test
	public void clinet() {

		Socket socket = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			// 1.创建一个Socket的对象，通过构造器指明服务端的IP地址，以及其接收程序的端口号
			socket = new Socket(InetAddress.getByName("127.0.0.1"), 9060);
			// 2.getOutputStream()：发送数据，方法返回OutputStream的对象
			outputStream = socket.getOutputStream();
			// 3.具体的输出过程
			outputStream.write("我是客户端".getBytes());
			// 4.shutdownOutput():执行此方法，显式的告诉服务端发送完毕！
			socket.shutdownOutput();
			
			//读取流
			inputStream = socket.getInputStream();
			byte[] b = new byte[20];
			int len;
			while ((len = inputStream.read(b)) != -1) {
				String str = new String(b, 0, len);
				System.out.print(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 4.关闭相应的流和Socket对象
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 服务端
	@Test
	public void server() {

		ServerSocket serverSocket = null;
		Socket socket = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			// 1.创建一个ServerSocket的对象，通过构造器指明自身的端口号
			serverSocket = new ServerSocket(9060);
			// 2.调用其accept()方法，返回一个Socket的对象
			socket = serverSocket.accept();
			// 3.调用Socket对象的getInputStream()获取一个从客户端发送过来的输入流
			inputStream = socket.getInputStream();
			// 4.对获取的输入流进行的操作
			byte[] b = new byte[10];
			int len = 0;
			while ((len = inputStream.read(b)) != -1) {
				String string = new String(b, 0, len);
				System.out.println(string);
			}
			System.out.println("收到来自于" + socket.getInetAddress().getHostAddress() + "的连接");
			outputStream = socket.getOutputStream();
			outputStream.write("我已收到你的情意".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 5.关闭相应的流以及Socket、ServerSocket的对象
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}