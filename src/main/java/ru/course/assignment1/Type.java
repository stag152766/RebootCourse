package ru.course.assignment1;

import ru.course.assignment1.transport.Vehicle;

import java.util.ArrayList;
import java.util.List;
// Тип авто
public class Type {

    private String code;
    private double fuelConsumption;
    private double fuelPrice;
    private List<Vehicle> vehicles;

    public Type(String code, double fuelConsumption, double fuelPrice) {
        this.code = code;
        this.fuelConsumption = fuelConsumption;
        this.fuelPrice = fuelPrice;
        vehicles = new ArrayList<>();
    }

    /*
     * Расчет стоимости расходов по типу
     */
    public double getCost(){
        double totalDistance = 0;
        for(Vehicle vehicle: vehicles){
            totalDistance += vehicle.getDistance();
        }
        return totalDistance / fuelConsumption * fuelPrice;
    }

    public String getCode() {
        return code;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
