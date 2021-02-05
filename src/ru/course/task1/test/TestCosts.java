package ru.course.task1.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.course.task1.FunctionHelper;
import ru.course.task1.GasManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCosts {
    private static String[] text = new String[]{"C100_1-100", "C200_1-120-1200", "C300_1-120-30", "C400_1-80-20", "C100_2-50", "C200_2-40-1000", "C300_2-200-45", "C400_2-10-20", "C100_3-10", "C200_3-170-1100", "C300_3-150-29", "C400_3-100-28", "C100_1-300", "C200_1-100-750", "C300_1-32-15"};


    GasManager manager = new GasManager(text);
    FunctionHelper helper = new FunctionHelper();

    @BeforeEach
    void initAll() {
        manager.start();
    }

    @Test
    void testTotalCost() {
        assertEquals(5986.758260869565, manager.getTotalCost());
        assertEquals(1696.4800000000002, manager.getCostType("100"));
        assertEquals(1752.25, manager.getCostType("200"));
        assertEquals(2073.478260869565, manager.getCostType("300"));
        assertEquals(464.54999999999995, manager.getCostType("400"));
    }

    @Test
    void testMaxMinCost() {
        assertEquals("300", manager.getMaxCostType());
        assertEquals("400", manager.getMinCostType());
    }

    @Test
    void testGetInfo100(){
        assertEquals("\nType: Car, Number: 3, Distance: 10.0\n" +
                        "Type: Car, Number: 2, Distance: 50.0\n" +
                        "Type: Car, Number: 1, Distance: 400.0",
                helper.getInfo(manager.getGroups().stream().
                        filter(g -> g.getType().equals("100")).findAny().orElse(null)));

    }

    @Test
    void testGetInfo200(){
        assertEquals("\nType: Truck, Number: 2, Distance: 40.0, Capacity: 1000\n" +
                        "Type: Truck, Number: 3, Distance: 170.0, Capacity: 1100\n" +
                        "Type: Truck, Number: 1, Distance: 220.0, Capacity: 750",
                helper.getInfo(manager.getGroups().stream().
                        filter(g -> g.getType().equals("200")).findAny().orElse(null)));

    }

    @Test
    void testGetInfo300(){
        assertEquals("\nType: Passangers, Number: 3, Distance: 150.0, Capacity: 29\n" +
                        "Type: Passangers, Number: 1, Distance: 152.0, Capacity: 15\n" +
                        "Type: Passangers, Number: 2, Distance: 200.0, Capacity: 45",
                helper.getInfo(manager.getGroups().stream().
                        filter(g -> g.getType().equals("300")).findAny().orElse(null)));

    }

    @Test
    void testGetInfo400(){
        assertEquals("\nType: Crane, Number: 2, Distance: 10.0, Capacity: 20\n" +
                        "Type: Crane, Number: 1, Distance: 80.0, Capacity: 20\n" +
                        "Type: Crane, Number: 3, Distance: 100.0, Capacity: 28",
                helper.getInfo(manager.getGroups().stream().
                        filter(g -> g.getType().equals("400")).findAny().orElse(null)));

    }

}
