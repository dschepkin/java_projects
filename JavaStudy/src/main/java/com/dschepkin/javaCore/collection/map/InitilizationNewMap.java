package com.dschepkin.javaCore.collection.map;

import java.util.HashMap;
import java.util.Map;

public class InitilizationNewMap {
    public static void main(String[] args) {
        Map<Integer,Integer> integerMap = new HashMap<>();
        integerMap.put(1, 1);
        integerMap.put(2, 2);
        integerMap.put(4, 3);

        //new method
        Map<Integer, Integer> newMap = Map.of (
                1,1,
                2,2,
                3,3
        );
    }
}
