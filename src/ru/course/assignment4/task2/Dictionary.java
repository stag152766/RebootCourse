package ru.course.assignment4.task2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Задание 2
 * Необходимо реализовать приложение на вход которого поступает текст,
 * а на выходе выводится частотный словарь букв латинского(английского) алфавита.
 */

public class Dictionary {

    private static String sourseText = "Aaa bb cqwertyuiopasdfghjklzxcvbnm,. *&%@ !!1231ЯП";


    public static void main(String[] args) {


        // остаются только символы латиницы
        String letters = getLetters();
        HashMap<Character, Integer> set = new HashMap<>();

        // заполняетя хэшмап
        // если буква уже содержится, то число повторений увеличивается на 1
        // если нет, то добавляется новая запись
        for (char ch : letters.toCharArray()) {
            if (set.containsKey(ch)) {
                Integer value = set.get(ch);
                set.put(ch, ++value);
            } else
                set.put(ch, 1);
        }

        // вывод на печать
        for (Map.Entry<Character, Integer> entry : set.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    // вспомогательный метод для очистки входного текста
    // остаются только символы латиницы
    private static String getLetters() {
        StringBuilder sb = new StringBuilder();

        Pattern tokSplitter = Pattern.compile("[a-zA-Z]+");
        Matcher m = tokSplitter.matcher(sourseText);

        while (m.find()) {
            sb.append(m.group().toLowerCase());
        }
        return sb.toString();
    }

    
}
