package ru.course.assignment1.helpers;

import ru.course.assignment1.transport.*;
import ru.course.assignment1.*;
import ru.course.assignment1.users.Admin;
import ru.course.assignment1.users.Operator;
import ru.course.assignment1.users.User;

import java.util.*;


public class GsmManager {

    private String[] workShift;
    private List<Type> types = new ArrayList<>();
    private DataManager dataManager;
    private User user;
    private Builder builder;
    private Map<Vehicle, Integer> map;


    /*
     * Инициализация помощников
     */
    public void init() {
        this.dataManager = new DataManager();
        this.builder = new Builder();

        // если смена не пустая, то создаем авто
        if (workShift != null) {
            build();
            // иначе запускаем сессию
        } else {
            user = getUser();
            startSession();
        }

    }

    /*
     * Строим объекты
     */
    private void build() {
        // парсинг входных данных
        List<String[]> parsingData;
        parsingData = dataManager.parse(workShift);
        // создание авто и типов
        builder.setData(parsingData);
        types = builder.getTypes();
    }


    /*
     * Работа основного потока программы
     */
    public void startSession() {

        boolean next = true;

        while (next) {
            System.out.print("Введите команду: ");
            String command = dataManager.readCommand();

            switch (command) {
                // Начало смены
                case "start": {
                    // заполнение списка авто - пробега
                    user.startShift();
                    // создание авто
                    Object[] arr = ((Admin) user).getInputStream().toArray();
                    workShift = Arrays.copyOf(arr, arr.length, String[].class);
                    build();
                    // назначение водителей
                    map = appointDriver();
                    break;
                }
                // Вывод итоговых результатов по смене
                case "ps": {
                    if (user instanceof Admin) {
                        // заполнение смены с консоли
                        calc();
                    }
                }
                // Завершить работу программы
                case "exit": {
                    next = false;
                    break;
                }
                // Показать список архивных файлов смены
                case "sf": {
                    if (user instanceof Admin) {
                        ((Admin) user).showFiles();
                    }
                }
                // Вывод списка архивных файлов смен
                case "pf": {
                    if (user instanceof Admin) {
                        System.out.println("Введите путь до файла: ");
                        String path = dataManager.readCommand();
                        ((Admin) user).openFile(path);

                    }
                }
                // Вывод списка водителей
                case "drivers": {
                    getDriversList();
                }
            }
        }


    }

    /*
     * Вывод информации по каждому водителю, о
     * количестве заработанных средств и авто
     */
    private void getDriversList() {
        if (!map.isEmpty()) {
            for (Map.Entry<Vehicle, Integer> entry : map.entrySet()) {
                Vehicle vehicle = entry.getKey();
                String car = "C" + vehicle.getClass().getSimpleName() + "_"
                        + vehicle.getRegNumber();
                int driverID = entry.getValue();
                int rate = 5;
                double salary = vehicle.getDistance() * rate;
                System.out.println("Водитель " + driverID +
                        " заработал средств за смену " + salary
                        +" на автомобиле " + car);
            }
        }
    }



    /*
     * Назначение водителей
     */

    public Map<Vehicle, Integer> appointDriver() {

        map = new HashMap<>();

        // заполнение Map автомобилями
        for (Type type : types) {
            List<Vehicle> vehicles = type.getVehicles();
            for (Vehicle vehicle : vehicles) {
                map.put(vehicle, null);
            }
        }

        // назначение водителя для каждого авто
        // пока есть авто без водителя
        while (map.containsValue(null)) {

            // для каждого авто без водителя
            for (Map.Entry<Vehicle, Integer> entry : map.entrySet()) {
                if (entry.getValue() == null) {
                    Vehicle vehicle = entry.getKey();
                    String car = "C" + vehicle.getClass().getSimpleName() + "_" + vehicle.getRegNumber();

                    // запрос нового водителя
                    System.out.print("Укажите ID водителя для авто " + car + ": ");
                    int driverID = Integer.parseInt(dataManager.readCommand());

                    // если водитель уже назначен для авто
                    for (Map.Entry<Vehicle, Integer> entry2 : map.entrySet()) {

                        // то снинаем его со текущего авто
                        if (entry2.getValue() != null && entry2.getValue().equals(driverID)) {
                            entry2.setValue(null);
                        }
                    }
                    // назначаем водителя на новое авто
                    entry.setValue(driverID);

                }
            }

        }
        return map;
    }


    /*
     * Проведение расчетов
     */
    public void calc() {
        if (workShift == null)
            return;
        System.out.println("Общая стоимость расходов на ГСМ: " + getTotalCosts());
        getAllTypesCost();
        System.out.println("Тип авто имеющий наибольшую стоимость расходов: " + getMaxType());
        System.out.println("Тип авто имеющий наименьшую стоимость расходов: " + getMinType());

        // вывод информации по запрашиваемому типу авто
        System.out.print("Введите код авто для получения информации о каждом авто: ");
        getInfoType(dataManager.readCommand());

    }


    /*
     * Авторизация пользователя
     */
    private User getUser() {
        Boolean temp = true;
        User user = null;

        while (temp) {
            System.out.print("Введите логин: ");
            String login = dataManager.readCommand();

            // создание объекта пользователя взависимости от логина
            if (login.equalsIgnoreCase("operator")) {
                user = new Operator();
                temp = false;
            } else if (login.equalsIgnoreCase("admin")) {
                user = new Admin();
                temp = false;
            }
        }
        return user;
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
        System.out.println("Расходы на каждый класс авто:");
        for (Type type : types) {
            System.out.println("Тип " + type.getCode() + ": " + getCostType(type.getCode()));
        }
    }


    /*
     * Расчет стоимости расходов на конкретный тип
     */
    public double getCostType(String code) {
        double sum = 0;
        for (Type type : types) {
            if (type.getCode().equals(code)) {
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

    public void setWorkShift(String[] workShift) {
        this.workShift = workShift;
    }

}
