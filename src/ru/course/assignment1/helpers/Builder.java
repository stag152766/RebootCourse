package ru.course.assignment1.helpers;

import ru.course.assignment1.*;

import java.util.*;
import ru.course.assignment1.transport.*;

public class Builder {
    java.util.List<String[]> text;
    List<Type> types = new ArrayList<>();

    public void setData(List<String[]> text) {
        this.text = text;
    }

    /*
     * Создание всех типов авто
     */
    public void createTypes() {
        types.add(new Type("100", 12.5, 46.1));
        types.add(new Type("200", 12, 48.9));
        types.add(new Type("300", 11.5, 47.5));
        types.add(new Type("400", 20, 48.9));
    }


    /*
     * Создание всех авто
     */
    public void createVehicles() {

        // чтение параметров
        for (String[] item : text) {
            String code = item[1];
            String regNumber = item[2];
            double distance = Double.parseDouble(item[3]);
            double capacity = 0;

            // проверка наличия доп параметра в строке
            if (item.length > 4) {
                capacity = Double.parseDouble(item[4]);
            }

            // поиск типа по коду
            for (Type type : types) {
                if (type.getCode().equals(code)) {
                    // получение списка авто по типу
                    List<Vehicle> vehicles = type.getVehicles();


                    Vehicle vehicle = null;
                    if (vehicles.size() == 0) {
                        vehicle = createVehicle(code, regNumber, distance, capacity);
                        vehicles.add(vehicle);
                    } else {
                        // поиск авто по гос номеру
                        for (Vehicle v : vehicles) {
                            // если есть, то дистанции и доп параметр суммируются
                            if (v.getRegNumber().equals(regNumber)) {
                                v.addDistance(distance);
                                v.addCapacity(capacity);
                                break;
                            } else {
                                // иначе создается новый авто
                                vehicle = createVehicle(code, regNumber, distance, capacity);
                                vehicles.add(vehicle);
                                break;
                            }
                        }

                    }
                }
            }
        }
    }


    /*
     * Создание конкретного авто
     */
    private Vehicle createVehicle(String code, String regNumber, double distance, double capacity) {
        Vehicle vehicle = null;
        switch (code) {
            case ("100"):
                vehicle = new Car(regNumber, distance, code);
                break;
            case ("200"):
                vehicle = new Truck(regNumber, distance, capacity, code);
                break;
            case ("300"):
                vehicle = new Bus(regNumber, distance, capacity,code);
                break;
            case ("400"):
                vehicle = new Crane(regNumber, distance, capacity, code);
                break;
        }
        return vehicle;
    }



    public List<Type> getTypes() {
        createTypes();
        createVehicles();
        return types;
    }
}
