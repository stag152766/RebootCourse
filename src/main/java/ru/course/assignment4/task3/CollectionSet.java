package ru.course.assignment4.task3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/*
 * Задание 3
 * Напишите метод, который на вход получает коллекцию объектов,
 * а возвращает коллекцию уже без дубликатов.
 * <T> Collection<T> removeDuplicates(Collection<T> collection)
 */


public class CollectionSet {

    public static void main(String[] args) {

        Collection<String> collection = new ArrayList<>();
        collection.add("Monday");
        collection.add("Tuesday");
        collection.add("Tuesday");
        System.out.print(removeDuplicates(collection).toString());
    }

    public static <T> Collection<T> removeDuplicates(Collection<T> collection){
         return new HashSet<>(collection);
    }
}
