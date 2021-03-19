package ru.course.assignment5.task2;


import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyStorage {

    private static final int MAX_AMOUNT = 20;
    private int currAmount;
    private Lock lock = new ReentrantLock();

    public MyStorage(int currAmount) {
        this.currAmount = currAmount;
    }

    public static void main(String[] args) throws InterruptedException {
        MyStorage storage = new MyStorage(10);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    storage.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    storage.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();


    }

    // поставка товара
    public void produce() throws InterruptedException {
        while (true) {
            lock.lock();
            System.out.println("Остаток " + currAmount);

            int amount = getRandom();

            // если склад не полный, то добавляем
            if (currAmount < MAX_AMOUNT) {
                currAmount += amount;

                // если склад переполнен, то возвращаем излишки
                if (currAmount > MAX_AMOUNT) {
                    amount -= (currAmount - MAX_AMOUNT);
                    currAmount = MAX_AMOUNT;
                }

                System.out.println("\t\tПривезли " + amount);
            } else {
                System.out.println("Склад полный");
            }

            lock.unlock();
            Thread.sleep(1000);

        }


    }

    // покупка товара
    public void consume() throws InterruptedException {
        Thread.sleep(500);
        while (true) {
            lock.lock();
            //System.out.println("Остаток " + currAmount);

            int amount = getRandom();

            // покупаем если склад не пустой
            if (currAmount > 0) {
                // если товара не достаточно, то покупаем сколько есть
                if (currAmount <= amount) {
                    amount = currAmount;
                    currAmount = 0;
                } else {
                    currAmount -= amount;
                }
                System.out.println("\t\t\t\tКупили " + amount);

            } else if (currAmount == 0) {
                System.out.println("Склад пустой");
            }
            lock.unlock();
            Thread.sleep(1000);
        }

    }

    // количество поставки и покупки в пределах от 1 до 6
    // так как покупать или привозить 0 не целесообразно
    private int getRandom() {
        Random random = new Random();
        return random.nextInt(5) + 1;
    }


}


