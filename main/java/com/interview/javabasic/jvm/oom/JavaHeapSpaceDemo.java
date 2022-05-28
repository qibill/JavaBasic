package com.interview.javabasic.jvm.oom;

/**
 * @author qibill
 * @date 2021/5/15 0015 下午 9:16
 */
public class JavaHeapSpaceDemo {

    /**
     * -Xms10m -Xmx10m
     *
     * @param args
     */
    public static void main(String[] args) {
        byte[] array = new byte[80 * 1024 * 1024];
        while (true) {
        }
    }
}
