package ru.course.assignment4.task4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Задание 4
 * Напишите метод, который получает на вход Map<K, V> и возвращает Map,
 * где ключи и значения поменяны местами. Так как значения могут совпадать,
 * то тип значения в Map будет уже не K, а Collection<K>: Map<V, Collection<K>>
 */

public class InvertedMap {

    public static void main(String[] args) {
        Map<String, Integer> week = new HashMap<>();
        week.put("Monday", 1);
        week.put("Tuesday", 1);
        week.put("Wednesday", 3);
        week.put("Thursday", 4);
        week.put("Friday", 5);
        week.put("Saturday", 6);
        week.put("Sunday", 7);

        // Вывод на печать исходной Map
        for (Map.Entry<String, Integer> entry : week.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        // Вывод на печать измененной Map
        for (Map.Entry<Integer, Set<String>> entry : invert(week).entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    // метод возвращает Map, в которой ключи и значения поменяны местам
    private static Map<Integer, Set<String>> invert(Map<String, Integer> oldMap) {
        Map<Integer, Set<String>> newMap = new HashMap<>();

        // перебор старой Map
        for (Map.Entry<String, Integer> oldEntry : oldMap.entrySet()) {

            // добавление первого элемента в Map
            if (newMap.size() == 0) {
                Set<String> newSet = new HashSet<>();
                newSet.add(oldEntry.getKey());
                newMap.put(oldEntry.getValue(), newSet);
                continue;
            }

            // поиск ключа
            if (newMap.containsKey(oldEntry.getValue())) {
                // если ключ найден, то добавление значение в Set
                Set<String> currSet = newMap.get(oldEntry.getValue());
                currSet.add(oldEntry.getKey());
                newMap.put(oldEntry.getValue(), currSet);
            } else {
                // если ключа нет, то добавление новой пары ключ-значение
                Set<String> newSet = new HashSet<>();
                newSet.add(oldEntry.getKey());
                newMap.put(oldEntry.getValue(), newSet);
            }
        }
        return newMap;
    }


}





