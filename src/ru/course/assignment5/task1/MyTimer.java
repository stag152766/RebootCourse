package ru.course.assignment5.task1;

public class MyTimer {

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer(12);


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    timer.every1sec();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    timer.every5sec();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        timer.finished();

    }
}

class Timer {
    private final int timeout;
    private int count;
    boolean stop;


    public Timer(int timeout) {
        this.timeout = timeout;
    }


    public void every1sec() throws InterruptedException {
        synchronized (this) {
            while (count < timeout) {
                if (count != 0 && count % 5 == 0) {
                    wait();
                }
                System.out.println((timeout - count) + " sec");
                count++;
                Thread.sleep(1000);
            }
            notify();
            stop = true;
        }
    }

    public void every5sec() throws InterruptedException {
        while (!stop) {
            synchronized (this) {
                if (count != 0 && count % 5 == 0) {
                    System.out.println("2nd - 5 sec");
                }
                notify();
            }
            Thread.sleep(1000);
        }
    }

    public void finished() {
        System.out.println("Time is out!");
    }
}
