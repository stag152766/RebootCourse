package ru.course.task1;

import java.io.IOException;

public class GasCalculator {

    private static String[] text = new String[]{"C100_1-100", "C200_1-120-1200", "C300_1-120-30", "C400_1-80-20", "C100_2-50", "C200_2-40-1000", "C300_2-200-45", "C400_2-10-20", "C100_3-10", "C200_3-170-1100", "C300_3-150-29", "C400_3-100-28", "C100_1-300", "C200_1-100-750", "C300_1-32-15"};

    public static void main(String[] args) throws IOException {
        GasManager manager = new GasManager(text);
        manager.start();

        FunctionHelper helper = new FunctionHelper();
        helper.start(manager);

    }
}
