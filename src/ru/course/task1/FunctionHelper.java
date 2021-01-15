package ru.course.task1;


import ru.course.task1.vehicles.Vehicle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FunctionHelper {

    public void start(GasManager manager) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();


        List<Group> groups = manager.getGroups();
        for (Group group : groups) {
            if (group.getType().equals(input)) {
                getInfo(group);
            }
        }


    }


    public void getInfo(Group group) {

        List<Vehicle> vehicles = group.getVehicles();
        Collections.sort(vehicles, Comparator.comparing(Vehicle::getDistance).thenComparing(Vehicle::getCapacity));
        for (Vehicle v : vehicles) {
            System.out.print("Type: " + v.getClass().getSimpleName() + ", ");
            System.out.print("Number: " + v.getNumber() + ", ");
            System.out.print("Distance: " + v.getDistance());
            if (v.getCapacity() != 0) {
                System.out.print(", Capacity: " + v.getCapacity());
            }
            System.out.print("\n");

        }


    }


}
