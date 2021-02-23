package ru.course.assignment1.transport;



public abstract class Vehicle {

    protected String regNumber;
    protected double distance;
    protected double capacity;
    protected String code;

    public void addDistance(double value) {
        this.distance += value;
    }

    public void addCapacity(double value) {
        this.capacity += value;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public double getDistance() {
        return distance;
    }

    public double getCapacity(){
        return capacity;
    }
}
