package ru.course.assignment1.helpers;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class DataManager {
    FileWriter fileWriter;
    FileReader fileReader;


    /*
     * Принимает текст, возвращает массив строк в формате [код, гос номер, пробег, доп параметр]
     */
    public List<String[]> parse(String[] textIn) {
        List<String[]> textOut = new ArrayList<>();

        for (String line : textIn) {
            String[] item = line.split("[-_C]");
            textOut.add(item);

        }
        return textOut;
    }


    /*
     * Чтение типа авто из консоли
     */
    public String readCommand() {
        String input = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }




    public void write(List<String> inputStream) {
        try {
            String fileName = "data/test.txt";
            fileWriter = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            System.out.println("Начало записи");
            for (String line : inputStream) {
                writer.newLine();
                writer.write(line);
            }
            writer.close();
            System.out.println("Данные сохранены в файл: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Просмотр списка архивных смен
     */
    public void openDir() {
        String path = "D:\\Admin\\Documents\\GitHub\\RebootCourse\\data";
        File dir = new File(path);
        File[] arrFiles = dir.listFiles();
        List<File> lst = Arrays.asList(arrFiles);
        System.out.println("Список архивных смен: ");
        System.out.println(lst);
    }

    /*
     * Просмотр архивного файла
     */
    public void openFile(String path) {
        try {
            fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);
            Stream<String> lst = reader.lines();
            while (reader.ready()) {
                lst.forEach(System.out::println);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
