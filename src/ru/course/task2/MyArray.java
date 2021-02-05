package ru.course.task2;


public class MyArray {

    private int[] array;

    public MyArray(int[] array) {
        this.array = array;
    }


    /*
     * добавление элемента в конец массива
     */
    public void add(int value) {
        int[] temp = new int[array.length + 1];

        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        temp[array.length] = value;
        this.array = temp;
        print();
    }

    /*
     * добавление элемента в определенную позицию массива
     */
    public void add(int value, int index) {
        if (index > array.length) {
            System.out.println("ArrayIndexOutOfBoundsException");
        }
        int[] temp = new int[array.length + 1];

        for (int i = 0; i < array.length; i++) {
            if (i < index) {
                temp[i] = array[i];
            } else {
                temp[i + 1] = array[i];
            }
            temp[index] = value;
        }
        this.array = temp;
        print();
    }


    /*
     * вывод количества элементов в массиве
     */
    public void getLength() {
        System.out.println(this.array.length);
    }


    /*
     * вывод на экран всего массива
     */
    public void print() {
        for (int i = 0; i < this.array.length; i++) {
            System.out.print(this.array[i] + " ");
        }
        System.out.println();
    }

    /*
     * удаление элемента массива по индексу
     */
    public void remove(int index) {
        if (index > array.length) {
            System.out.println("ArrayIndexOutOfBoundsException");
        }
        int[] temp = new int[array.length - 1];

        for (int i = 0; i < array.length; i++) {
            if (i < index) {
                temp[i] = array[i];
            } else if (i < temp.length) {
                temp[i] = array[i + 1];
            }
        }
        this.array = temp;
        print();
    }

    /*
     * изменение значения по его индексу
     */
    public void set(int value, int index) {
        if (index > array.length) {
            System.out.println("ArrayIndexOutOfBoundsException");
        }
        int[] temp = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        temp[index] = value;
        this.array = temp;
        print();
    }

    /*
     * функция сортировки массива по возрастанию без изменения исходного массива
     */
    public int[] sort() {
        int[] temp = array.clone();
        bubbleSort(temp);
        return temp;
    }

    /*
     * функция сортировки массива по убыванию без изменения исходного массива
     */
    public int[] reverse() {
        int[] temp = array.clone();
        changeToNegative(temp);
        bubbleSort(temp);
        changeToNegative(temp);
        return temp;
    }

    private void changeToNegative(int[] temp) {
        for (int i = 0; i < array.length; i++) {
            temp[i] = -temp[i];
        }
    }

    private void bubbleSort(int[] temp) {
        for (int out = array.length - 1; out >= 1; out--) {

            for (int in = 0; in < out; in++) {

                if (temp[in] > temp[in + 1]) {
                    int tmp = temp[in];
                    temp[in] = temp[in + 1];
                    temp[in + 1] = tmp;
                }
            }
        }
    }


    /*
     * функция вывода максимального элемента
     */
    public void getMaxValue() {
        int[] temp = array.clone();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (temp[i] > max) {
                max = temp[i];
            }
        }
        System.out.println(max);
    }


    /*
     * функция вывода минимального элемента
     */
    public void getMinValue() {
        int[] temp = array.clone();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (temp[i] < min) {
                min = temp[i];
            }
        }
        System.out.println(min);
    }


    /*
     * функция заполнения массива одинаковыми элементами
     */
    public void fill(int a) {
        int[] temp = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            temp[i] = a;
        }
        this.array = temp;
        print();
    }

}
