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
        setParser();
        createGroups();
        createVehicles();
        calculate();

    }


    public void setParser() {
        this.parser = new ParserHelper(text);
    }


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

    public Group initGroup(String type) {
        List<String> log = getLog(type);
        HashMap<String, Integer> carsMap = getDistanceByType(log, type);
        return new Group(type, carsMap);

    }

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


    public void createVehicles() {

        for (Group group : groups) {
            if (group.getType().equals("100")) {
                for (Map.Entry<String, Integer> entry : group.getMap().entrySet()) {
                    Vehicle newVehicle = new Car(group, entry.getKey(), entry.getValue());
                    int capacity = getCapacity(entry, "100");
                    newVehicle.setCapacity(capacity);
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


    private int getCapacity(Map.Entry<String, Integer> entry, String type) {
        int capacity = 0;
        String currNumber = entry.getKey();
        List<String> log = getLog(type);

        for (String line : log) {
            String[] vehicle = line.split("[-_C]");
            String nextNumber = vehicle[2];
            if (nextNumber.equals(currNumber) && vehicle.length > 4) {
                capacity = Integer.parseInt(vehicle[4]);
            }
        }
        return capacity;
    }


    private void calculate() {
        getTotalCost();
        getCostPerType();
        getMaxCostType();
        getMinCostType();
    }


    public HashMap<String, Integer> getDistanceByType(List<String> log, String type) {
        HashMap<String, Integer> vehicles = new HashMap<>();

        for (String line : log) {
            String[] vehicle = line.split("[-_C]");
            String number = vehicle[2];
            int distance = Integer.parseInt(vehicle[3]);
            if (vehicles.containsKey(number)) {
                int currDistance = vehicles.get(number);
                vehicles.put(number, currDistance + distance);
            } else
                vehicles.put(number, distance);
        }
        return vehicles;

    }

    public List<Group> getGroups() {
        return groups;
    }

    /*
    Общая стоимость расходов на ГСМ
     */
    public void getTotalCost() {
        double totalCost = 0;
        for (Group group : groups) {
            for (Vehicle vehicle : group.getVehicles()) {
                totalCost += vehicle.getCost();
            }
        }
        System.out.println("Total cost: " + totalCost);
    }

    /*
    Расходы на каждый класс авто
     */
    public void getCostPerType() {
        System.out.println("Cost of car: " + getCostType("100"));
        System.out.println("Cost of truck: " + getCostType("200"));
        System.out.println("Cost of passenger: " + getCostType("300"));
        System.out.println("Cost of crane: " + getCostType("400"));

    }

    private Double getCostType(String type) {
        double cost = 0;
        Group cars = groups.stream().filter(g -> g.getType().equals(type)).findAny().orElse(null);
        for (Vehicle car : cars.getVehicles()) {
            cost += car.getCost();
        }
        return cost;
    }


    /*
    Тип авто имеющий наибольшую стоимость расходов
     */
    public void getMaxCostType() {
        System.out.println("Type has max cost: " + groups.stream().max(
                Comparator.comparing(g -> g.getTotalCost())).get().getType());
    }

    /*
    Тип авто имеющий наименьшую стоимость расходов
    */
    public void getMinCostType() {
        System.out.println("Type has min cost: " + groups.stream().min(
                Comparator.comparing(g -> g.getTotalCost())).get().getType());

    }


}