package ru.course.task1;

import ru.course.task1.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Group {

    private String type;
    private List<Vehicle> vehicles;
    private HashMap<String, Integer> list;


    public Group(String type, HashMap<String, Integer> list) {
        this.type = type;
        this.list = list;
        this.vehicles = new ArrayList<>();
    }


    public double getTotalCost() {
        int distance = 0;
        for (Vehicle vehicle : vehicles) {
            distance += vehicle.getCost();
        }
        return distance;
    }

    public HashMap<String, Integer> getMap() {
        return list;
    }


    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }


    public String getType() {
        return type;
    }


    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
