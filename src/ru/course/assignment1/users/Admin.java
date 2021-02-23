package ru.course.assignment1.users;
import ru.course.assignment1.helpers.DataManager;

import java.util.ArrayList;
import java.util.List;

public class Admin implements User{

    private DataManager manager;
    List<String> inputStream;

    @Override
    public void startShift() {
        System.out.println("Начало смены");
        System.out.println("Введите данные в формате C(CODE_CAR)_гос номер-Пробег-(доп. параметр)");
        System.out.println("Введите submit для завершения");
        manager = new DataManager();
        inputStream = new ArrayList<>();
        boolean tmp = true;

        while (tmp) {
            String item = manager.readCommand();
            switch (item) {
                case "submit": {
                    tmp = false;
                    save();
                    break;
                }
                default:
                    inputStream.add(item);

            }
        }

    }

    @Override
    public void save() {

    }




    @Override
    public List<String> getInputStream() {
        return inputStream;
    }

    public void showFiles(){
        manager.openDir();
    }

    public void openFile(String path){
        manager.openFile(path);
    }

    public Admin() {
        this.manager = new DataManager();
    }
}
