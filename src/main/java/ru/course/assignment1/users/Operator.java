package ru.course.assignment1.users;

import ru.course.assignment1.helpers.DataManager;
import ru.course.assignment1.transport.Vehicle;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Operator implements User {

    private List<String> inputStream;
    private DataManager manager;

    /*
     * Оператор вносит данные в течение смены
     * В конце смены данные сохраняются в файл
     */
    @Override
    public void startShift() {
        System.out.println("Начало смены");
        System.out.println("Введите данные в формате C(CODE_CAR)_гос номер-Пробег-(доп. параметр)");
        System.out.println("Введите submit для завершения");
        manager = new DataManager();
        inputStream = new ArrayList<>();
        boolean tmp = true;

        while (tmp) {
            String item = manager.readCommand();
            switch (item) {
                case "submit": {
                    System.out.println("Конец смены");
                    tmp = false;
                    save();
                    break;
                }
                default:
                    inputStream.add(item);

            }
        }

    }


    @Override
    public void save() {
        manager.write(inputStream);
    }




    public List<String> getInputStream() {
        return inputStream;
    }
}
