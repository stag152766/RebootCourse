package ru.course.myclass;

import java.util.*;

public class Test {

    public static void main(String[] args) {

        Map<Integer, String> map = new HashMap<>();
        map.put(1, null);
        map.put(2, null);
        map.put(4, "stop");
        map.put(null, "dscc");
        while(map.containsKey(null))
        System.out.println(map);


    }
}
