package ru.course.assignment1.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DataParser {
    private String[] textIn;

    public DataParser(String[] textIn) {
        this.textIn = textIn;
    }

    /*
     * Принимает текст, возвращает массив строк в формате [код, гос номер, пробег, доп параметр]
     */
    public List<String[]> run() {
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
    public String getInputCode() {
        String input = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }


}
