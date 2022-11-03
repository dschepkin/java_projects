package com.dschepkin.library;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Iterable {
    public static void main(String... args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(10, "Dima");
        map.put(20, "Olesya");
        map.put(30, "Maxim");

        //1
        var iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println("Key=" + entry.getKey() + " Value=" + entry.getValue());
        }
            //2
//        var entrySet = map.entrySet();
//        for (Map.Entry<Integer, String> entry : entrySet) {
//            System.out.println("key="+entry.getKey()+", value="+entry.getValue());
//        }

        //lambda
        System.out.println("...Lambda");
        map.forEach((k,v) -> System.out.println("key="+k+", value="+v));
        map.keySet().forEach(k -> System.out.println("key="+k));
        map.values().forEach(v -> System.out.println("Value="+v));

        System.out.println();
        //Stream API
        System.out.println("...Stream API");
        map.entrySet().stream()
                .forEach(e -> System.out.println("Key "+e.getKey()+" Value "+e.getValue()));
    }
}