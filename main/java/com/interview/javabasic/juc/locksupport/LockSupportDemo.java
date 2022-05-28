package com.interview.javabasic.juc.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author qibill
 * @date 2021/5/12 0012 下午 3:57
 */
public class LockSupportDemo {

    public static void main(String[] args) {
        Thread a = new Thread(()->{
//			try {
//				TimeUnit.SECONDS.sleep(2);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
            System.out.println(Thread.currentThread().getName() + " come in. " + System.currentTimeMillis());
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " 换醒. " + System.currentTimeMillis());
        }, "Thread A");
        a.start();

        Thread b = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.unpark(a);
            System.out.println(Thread.currentThread().getName()+" 通知.");
        }, "Thread B");
        b.start();
    }

}

