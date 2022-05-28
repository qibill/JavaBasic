package com.interview.javabasic.runtime;

/**
 * @author qibill
 * @date 2021/5/21 0021 下午 12:37
 */
public class RuntimeDemo {

    public static void main(String[] args) {
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println("CPU的核心线程数" + i);
    }
}
