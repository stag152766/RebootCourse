package ru.course.task2;

public class Tester {

    public static void main(String[] args) {
        int[] array = {33,6,9,4};
        MyArray myArray = new MyArray(array);
        myArray.printArray();

        myArray.add(5);
        myArray.printArray();

        myArray.add(6,0);
        myArray.printArray();

        myArray.getLength();

        myArray.remove(0);
        myArray.printArray();

        myArray.set(7,4);
        myArray.printArray();

        myArray.getMaxValue();
        myArray.printArray();

        myArray.getMinValue();
        myArray.printArray();

        int[] sortedArrayASC = myArray.sort();
        for (int i: sortedArrayASC){
            System.out.print(i + " ");
        }
        System.out.println();


        int[] sortedArrayDESC = myArray.sortReverseOrder();
        for (int i: sortedArrayDESC){
            System.out.print(i + " ");
        }
        System.out.println();

        myArray.setArray(1);
        myArray.printArray();





    }

}

