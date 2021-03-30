package ru.course.assignment2;

public class Tester {

    public static void main(String[] args) {
        int[] array = {33, 6, 9, 4};
        MyArray myArray = new MyArray(array);
        myArray.add(5);
        myArray.add(6, 0);
        myArray.getLength();
        myArray.remove(0);
        myArray.set(7, 4);
        myArray.getMaxValue();
        myArray.getMinValue();

        MyArray sorted = new MyArray(myArray.sort());
        sorted.print();

        MyArray reversed = new MyArray(myArray.reverse());
        reversed.print();

        myArray.fill(1);


    }

}

