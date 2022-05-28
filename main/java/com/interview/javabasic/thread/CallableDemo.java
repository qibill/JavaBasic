package com.interview.javabasic.thread;

import java.util.concurrent.*;

/**
 * @author qibill
 * @date 2021/5/13 0013 下午 5:26
 */
public class CallableDemo {

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程在进行计算");
            Thread.sleep(1000);
            int sum = 0;
            for (int i = 0; i < 10000; i++)
                sum += i;
            return sum;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();
        //使用FutureTask
        Callable<Integer> task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        executor.submit(futureTask);

        //使用Future
//        Callable<Integer> call = new Task();
//        Future<Integer> future = executor.submit(call);

        executor.shutdown();
        System.out.println("主线程在执行任务");
        Thread.sleep(2000);
        try {
            System.out.println("task运行结果" + futureTask.get()); //future.get()
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("所有任务执行完毕");
    }
}
