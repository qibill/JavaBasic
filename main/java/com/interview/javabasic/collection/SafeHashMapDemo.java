package com.interview.javabasic.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SafeHashMapDemo {
    public static void main(String[] args) {
        Map<String, String> hashMap = new HashMap<>();
        Map<String, String> safeMap = Collections.synchronizedMap(hashMap);

        Map<String, String> hashtable = new Hashtable<>();
        Map<String, String> concurrentHashMap = new ConcurrentHashMap<>();
    }
}
