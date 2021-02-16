package ru.course.assignment4.task5;

import java.util.HashMap;


/*
 * Задание 5
 * Необходимо написать функцию, которая на вход получает массив строк,
 * в формате имя_игрока количество_очков. Необходимо вывести победителя данной игры.
 *  Победителем считается тот, кто раньше набрал максимальное количество очков.
 */

public class Game {


    public static void main(String[] args) {
        String[] game1 = {"Ivan 5", "Petr 3", "Alex 10", "Petr 8", "Ivan 6", "Alex 5", "Ivan 1", "Petr 5", "Alex 1"};
        String[] game2 = {"Ivan 5", "Petr 3", "Alex 10", "Petr 8", "Ivan 6", "Alex 5", "Ivan 1", "Alex 1", "Petr 5"};
        Game game = new Game();
        game.getWinner(game1);
        game.getWinner(game2);

    }

    private void getWinner(String[] array) {

        // определение максимального количества очков
        HashMap<String, Integer> temp = new HashMap<>();
        int max = 0;
        for (String note : array) {
            String[] item = note.split(" ");
                String name = item[0];
                int nextPoints = Integer.parseInt(item[1]);

                // поиск существующего игрока
                if (temp.containsKey(name)){
                    int earnPoint = temp.get(name);
                    earnPoint += nextPoints;
                    temp.put(name, earnPoint);
                    if (max < earnPoint){
                        max = earnPoint;
                    }
                } else {
                    // добавление нового игрока
                    temp.put(name, nextPoints);
                }
        }

        // Вывод победителя, который раньше набрал максимальное количество очков
        String winner = "";
        HashMap<String, Integer> scores = new HashMap<>();
        for (String note : array) {
            String[] item = note.split(" ");
            String name = item[0];
            int nextPoints = Integer.parseInt(item[1]);

            // поиск существующего игрока
            if (scores.containsKey(name)){
                int earnPoint = scores.get(name);
                earnPoint += nextPoints;
                scores.put(name, earnPoint);
                // если игрок первый набрал максимальное количество очков, то он победитель
                 if (earnPoint == max){
                    winner = name;
                    break;
                }
            } else {
                // добавление нового игрока
                scores.put(name, nextPoints);
            }
        }

        System.out.println(winner);
    }
}
