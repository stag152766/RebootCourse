package ru.course.task1;

import java.util.ArrayList;
import java.util.List;

public class ParserHelper {

    private String[] text;

    private List<String> carsLog = new ArrayList<>();
    private List<String> trucksLog = new ArrayList<>();
    private List<String> passengersLog = new ArrayList<>();
    private List<String> craneLog = new ArrayList<>();


    public ParserHelper(String[] text) {
        this.text = text;
        parseData();
    }


    public void parseData() {
        for (String line : text) {
            String[] vehicle = line.split("[-_C]");

            String type = vehicle[1];
            switch (type) {
                case ("100"):
                    carsLog.add(line);
                    break;
                case ("200"):
                    trucksLog.add(line);
                    break;
                case ("300"):
                    passengersLog.add(line);
                    break;
                case ("400"):
                    craneLog.add(line);
                    break;
            }
        }
    }


    public List<String> getCarsLog() {
        return carsLog;
    }


    public List<String> getTrucksLog() {
        return trucksLog;
    }


    public List<String> getPassengersLog() {
        return passengersLog;
    }


    public List<String> getCraneLog() {
        return craneLog;
    }
}
