package com.interview.javabasic.thread.revalue;

public class CycleWait implements Runnable{
    private String value;
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        value = "we have data now";
    }

    public static void main(String[] args) throws InterruptedException {
        CycleWait cw = new CycleWait();
        Thread t = new Thread(cw);
        t.start();
        //主线程等待法
//        while (cw.value == null){
//            Thread.currentThread().sleep(100);
//        }
        //使用Thread类的join()阻塞当前线程以等待子线程处理完毕
        t.join();
        System.out.println("value : " + cw.value);
    }
}
