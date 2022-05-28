package com.interview.javabasic.juc.locksupport;

/**
 * @author qibill
 * @date 2021/5/12 0012 下午 3:56
 */
public class WaitNotifyDemo {

    static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName()+" come in.");
                try {
                    lock.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+" 换醒.");
        }, "Thread A").start();

        new Thread(()->{
            synchronized (lock) {
                lock.notify();
                System.out.println(Thread.currentThread().getName()+" 通知.");
            }
        }, "Thread B").start();
    }
}
