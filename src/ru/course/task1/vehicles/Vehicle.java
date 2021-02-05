package ru.course.task1.vehicles;

import ru.course.task1.Group;


public abstract class Vehicle {

    private Group type;
    private double gasPrice;
    private double fuelConsumprion;
    private double distance;
    private String number;
    private int capacity = 0;


    public Vehicle(Group type, String number, double gasPrice, double fuelConsumprion, double distance) {
        this.type = type;
        this.gasPrice = gasPrice;
        this.fuelConsumprion = fuelConsumprion;
        this.distance = distance;
        this.number = number;
    }

    /*
     *  Расчет стоимости расходов по одному авто
     */
    public double getCost(){
        return (distance / fuelConsumprion) * gasPrice;
    }


    public double getDistance() {
        return distance;
    }


    public String getNumber() {
        return number;
    }


    public int getCapacity() {
        return capacity;
    }


    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
