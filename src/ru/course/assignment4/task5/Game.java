package ru.course.assignment4.task5;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Задание 5
 * Необходимо написать функцию, которая на вход получает массив строк,
 * в формате имя_игрока количество_очков. Необходимо вывести победителя данной игры.
 *  Победителем считается тот, кто раньше набрал максимальное количество очков.
 */

public class Game {
    private static String[] sourseText = {"Ivan 5", "Petr 3", "Alex 10", "Petr 8", "Ivan 6", "Alex 5", "Ivan 1", "Petr 5", "Alex 1"};


    public static void main(String[] args) {
        LinkedHashMap<String, Integer> lhm = new LinkedHashMap<>();

        for (String note : sourseText) {
            String[] item = note.split(" ");
                String name = item[0];
                int nextPoints = Integer.parseInt(item[1]);
                if (lhm.containsKey(name)){
                    int earnPoint = lhm.get(name);
                    lhm.put(name, earnPoint+nextPoints);
                    break;
                }
                lhm.put(name, nextPoints);
            }

        // Вывод победителя, который раньше набрал максимальное количество очков
        String winner = "";
        int max = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : lhm.entrySet()) {
            if (max < entry.getValue()){
                max = entry.getValue();
                winner = entry.getKey();
            }
        }
        System.out.println(winner + " - " + max);
    }
}
