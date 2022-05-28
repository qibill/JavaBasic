package com.interview.javabasic.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOPlainEchoServer implements PlainEchoServer {
    @Override
    public void serve(int port) throws IOException {
        System.out.println("Listening for connections on port " + port);
        ServerSocketChannel channel = ServerSocketChannel.open();
        ServerSocket socket = channel.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        socket.bind(address);
        channel.configureBlocking(false);
        Selector selector = Selector.open();
        //将channel注册到Selector里，并说明让Selector关注的点，这里是关注建立链接这个事件
        channel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            try {
                //阻塞等待就绪的Channel,即没有与客户端建立链接前就一直轮询
                selector.select();
            } catch (IOException ex){
                ex.printStackTrace();
                //代码省略的部分是结合业务，正确处理异常的逻辑
                break;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                //将就绪的SelectionKey从Selector中移除，因为马上就要去处理它，防止重复执行
                iterator.remove();
                //若SelectionKey处在Acceptable状态
                if (key.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel)key.channel();
                    //接受客户端的链接
                    SocketChannel client = server.accept();
                    System.out.println("Accepted connection from " + client);
                    client.configureBlocking(false);
                    //向selector注册SocketChannel，主要关注读写，并传入一个ByteBuffer实例供读写缓存
                    client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, ByteBuffer.allocate(100));
                }
                if (key.isReadable()){
                    SocketChannel client = (SocketChannel)key.channel();
                    ByteBuffer output = (ByteBuffer) key.attachment();
                    client.read(output);
                }
                if (key.isWritable()){
                    SocketChannel client = (SocketChannel)key.channel();
                    ByteBuffer output = (ByteBuffer) key.attachment();
                    client.write(output);
                    output.compact();
                }
            }
        }
    }

}
