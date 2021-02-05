package ru.course.task1;

import ru.course.task1.vehicles.*;

import java.util.*;


public class GasManager {

    private String[] text;
    private ParserHelper parser;
    private List<Group> groups;


    public GasManager(String[] text) {
        this.text = text;
    }


    public void start() {
        // чтение входных данных
        setParser();
        // инициализация типов и авто
        createGroups();
        createVehicles();
        // калькуляция затрат
        calculate();
    }


    /*
     * Входящий файл парсится за один проход,
     * в результате информация в разрезе каждого типа авто
     * хранится в соответствующем поле класса ParserHelper
     */
    public void setParser() {
        this.parser = new ParserHelper(text);
    }

    /*
     * Создание и добавление группы в список
     */
    private void createGroups() {
        groups = new ArrayList<>();
        Group cars = initGroup("100");
        Group truck = initGroup("200");
        Group passenger = initGroup("300");
        Group crane = initGroup("400");
        groups.add(cars);
        groups.add(truck);
        groups.add(passenger);
        groups.add(crane);
    }

    /*
     * Создание типа авто
     * Тип содержит поля код и список авто (гос номер - пробег)
     */
    public Group initGroup(String type) {
        List<String> log = getLog(type);
        HashMap<String, Integer> carsMap = getDistanceByType(log, type);
        return new Group(type, carsMap);

    }


    /*
     * Получение входных данных по типу авто
     */
    private List<String> getLog(String type) {
        List<String> log = null;
        if (type.equals("100")) {
            log = parser.getCarsLog();
        } else if (type.equals("200")) {
            log = parser.getTrucksLog();
        } else if (type.equals("300")) {
            log = parser.getPassengersLog();
        } else if (type.equals("400"))
            log = parser.getCraneLog();
        return log;
    }


    /*
     * Создание всех авто
     */
    public void createVehicles() {

        for (Group group : groups) {
            if (group.getType().equals("100")) {
                for (Map.Entry<String, Integer> entry : group.getMap().entrySet()) {
                    // создание авто
                    Vehicle newVehicle = new Car(group, entry.getKey(), entry.getValue());
                    // поиск доп параметров
                    int capacity = getCapacity(entry, "100");
                    // добавление доп параметра в авто
                    newVehicle.setCapacity(capacity);
                    // добавление авто в список авто типа
                    group.addVehicle(newVehicle);
                }
            } else if (group.getType().equals("200")) {
                for (Map.Entry<String, Integer> entry : group.getMap().entrySet()) {
                    Vehicle newVehicle = new Truck(group, entry.getKey(), entry.getValue());
                    int capacity = getCapacity(entry, "200");
                    newVehicle.setCapacity(capacity);
                    group.addVehicle(newVehicle);
                }
            } else if (group.getType().equals("300")) {
                for (Map.Entry<String, Integer> entry : group.getMap().entrySet()) {
                    Vehicle newVehicle = new Passangers(group, entry.getKey(), entry.getValue());
                    int capacity = getCapacity(entry, "300");
                    newVehicle.setCapacity(capacity);
                    group.addVehicle(newVehicle);
                }
            } else if (group.getType().equals("400")) {
                for (Map.Entry<String, Integer> entry : group.getMap().entrySet()) {
                    Vehicle newVehicle = new Crane(group, entry.getKey(), entry.getValue());

                    int capacity = getCapacity(entry, "400");
                    newVehicle.setCapacity(capacity);
                    group.addVehicle(newVehicle);
                }
            }

        }


    }

    /*
     * Получение значение доп параметра авто (число пассажиров или вес поднятых грузов)
     */
    private int getCapacity(Map.Entry<String, Integer> entry, String type) {
        int capacity = 0;
        // гос номер
        String currNumber = entry.getKey();
        // исходные данные по конкретному типу авто
        List<String> log = getLog(type);

        for (String line : log) {
            String[] vehicle = line.split("[-_C]");
            //гос номер
            String nextNumber = vehicle[2];
            // проверка наличия доп параметра
            // если доп параметр есть, то значение суммируется
            if (nextNumber.equals(currNumber) && vehicle.length > 4) {
                capacity = Integer.parseInt(vehicle[4]);
            }
        }
        return capacity;
    }

    // Калькуляция стоимости расходов
    private void calculate() {
        getTotalCost();
        getCostPerType();
        getMaxCostType();
        getMinCostType();
    }


    /*
     * Создание карты "гос номер авто - пробег"
     */
    public HashMap<String, Integer> getDistanceByType(List<String> log, String type) {
        HashMap<String, Integer> vehicles = new HashMap<>();

        for (String line : log) {
            String[] vehicle = line.split("[-_C]");
            String number = vehicle[2];
            int distance = Integer.parseInt(vehicle[3]);

            // проверка наличия нескольких записей об одном авто
            // если записи есть, то суммируем пробег
            if (vehicles.containsKey(number)) {
                int currDistance = vehicles.get(number);
                vehicles.put(number, currDistance + distance);
            } else
                vehicles.put(number, distance);
        }
        return vehicles;

    }

    /*
     * Общая стоимость расходов на ГСМ
     */

    public double getTotalCost() {
        double totalCost = 0;
        for (Group group : groups) {
            for (Vehicle vehicle : group.getVehicles()) {
                totalCost += vehicle.getCost();
            }
        }
        System.out.println("Total cost: " + totalCost);
        return totalCost;
    }
    /*
     * Стоимость расходов на каждый тип авто
     */

    public void getCostPerType() {
        System.out.println("Cost of car: " + getCostType("100"));
        System.out.println("Cost of truck: " + getCostType("200"));
        System.out.println("Cost of passenger: " + getCostType("300"));
        System.out.println("Cost of crane: " + getCostType("400"));

    }
    /*
     * Стоимость расходов по конкретному типу авто
     */

    public double getCostType(String type) {
        double cost = 0;
        Group cars = groups.stream().filter(g -> g.getType().equals(type)).findAny().orElse(null);
        for (Vehicle car : cars.getVehicles()) {
            cost += car.getCost();
        }
        return cost;
    }

    /*
     * Тип авто имеющий наибольшую стоимость расходов
     */

    public String getMaxCostType() {
        String type = groups.stream().max(
                Comparator.comparing(g -> g.getTotalCost())).get().getType();
        System.out.println("Type has max cost: " + type);
        return type;
    }
    /*
    * Тип авто имеющий наименьшую стоимость расходов
    */

    public String getMinCostType() {
        String type =  groups.stream().min(
                Comparator.comparing(g -> g.getTotalCost())).get().getType();
        System.out.println("Type has min cost: " + type);
        return type;
    }


    public List<Group> getGroups() {
        return groups;
    }
}