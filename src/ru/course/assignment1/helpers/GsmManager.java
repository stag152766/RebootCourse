package ru.course.assignment1.helpers;

import ru.course.assignment1.transport.*;
import ru.course.assignment1.*;
import java.util.*;


public class GsmManager {

    private String[] textIn;
    private List<Type> types = new ArrayList<>();
    private DataParser parser;

    public GsmManager(String[] textIn) {
        this.textIn = textIn;
    }


    /*
     * Создание и вызов вспомогательных классов
     */
    public void init() {
        // вызов парсера для разбора входящих данных
        parser = new DataParser(textIn);
        List<String[]> textOut = parser.run();

        // вызов класса создателя для создания авто и типов
        Creator creator = new Creator(textOut);
        types = creator.getTypes();
    }


    /*
     * Вывод на экран всех расходов
     */
    public void start() {
        System.out.println("Total cost: " + getTotalCosts());
        getAllTypesCost();
        System.out.println("Type has max cost: " + getMaxType());
        System.out.println("Type has min cost: " + getMinType());
        getInfoType(parser.getInputCode());

    }

    /*
     * Расчет общей стоимости расходов
     */
    public double getTotalCosts() {
        double sum = 0;
        for (Type type : types) {
            sum += type.getCost();
        }
        return sum;

    }

    /*
     * Расчет стоимости расходов на каждый тип
     */
    public void getAllTypesCost() {
        for (Type type : types) {
            System.out.println(type.getCode() + ": " + getCostType(type.getCode()));
        }
    }


    /*
     * Расчет стоимости расходов на конкретный тип
     */
    public double getCostType(String code) {
        double sum = 0;
        for (Type type : types){
            if (type.getCode().equals(code)){
                sum = type.getCost();

            }
        }
        return sum;
    }

    /*
     * Тип авто с наибольшими расходами
     */
    public String getMaxType() {
        double max = Double.MIN_VALUE;
        String code = "";
        for (Type type : types) {
            double totalCost = type.getCost();
            if (max < totalCost) {
                max = totalCost;
                code = type.getCode();
            }

        }
        return code;
    }

    /*
     * Тип авто с наименьшими расходами
     */
    public String getMinType() {
        double min = Double.MAX_VALUE;
        String code = "";
        for (Type type : types) {
            double totalCost = type.getCost();
            if (min > totalCost) {
                min = totalCost;
                code = type.getCode();
            }

        }
        return code;
    }

    /*
     *  Инфо о каждом авто c сортировкой по пробегу и доп параметру
     */
    public String getInfoType(String code) {
        String result = "";
        List<Vehicle> vehicles = new ArrayList<>();
        for (Type type : types) {
            if (type.getCode().equals(code)) {
                vehicles = type.getVehicles();
                Collections.sort(vehicles, Comparator.comparing(Vehicle::getDistance).thenComparing(Vehicle::getCapacity));
                for (Vehicle v : vehicles) {
                    result += "\nType: " + v.getClass().getSimpleName() + ", " +
                            "Number: " + v.getRegNumber() + ", " +
                            "Distance: " + v.getDistance();
                    if (v.getCapacity() != 0) {
                        result += ", Capacity: " + v.getCapacity();
                    }
                }
            }
        }
        System.out.println(result);
        return result;
    }


}