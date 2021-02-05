package ru.course.task1;


import ru.course.task1.vehicles.Vehicle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FunctionHelper {


    /*
     * Получение информации по типу авто
     */
    public void start(GasManager manager) {
        String input = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // поиск нужного типа авто
        List<Group> groups = manager.getGroups();
        for (Group group : groups) {
            if (group.getType().equals(input)) {
                // вывод инфо на печать
                getInfo(group);
            }
        }


    }

    /*
     * Функция которая в разрезе каждого типа авто выводит информацию о каждом авто
     */
    public String getInfo(Group group) {
        String result = "";
        List<Vehicle> vehicles = group.getVehicles();
        Collections.sort(vehicles, Comparator.comparing(Vehicle::getDistance).thenComparing(Vehicle::getCapacity));
        for (Vehicle v : vehicles) {
            result += "\nType: " + v.getClass().getSimpleName() + ", " +
                    "Number: " + v.getNumber() + ", "+
                    "Distance: " + v.getDistance();
            if (v.getCapacity() != 0) {
                result +=", Capacity: " + v.getCapacity();
            }

        }
        System.out.println(result);
        return result;

    }


}
