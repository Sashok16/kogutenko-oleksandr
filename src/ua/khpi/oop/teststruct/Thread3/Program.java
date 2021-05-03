package ua.khpi.oop.teststruct.Thread3;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                int a = scanner.nextInt();
                switch (a) {
                    case 1:
                        CommonResource commonResource = new CommonResource();
                        for (int i = 1; i < 5; i++) {

                            Thread t = new Thread(new CountThread(commonResource));
                            t.setName("Thread        " + i);
                            t.start();
                        }
                        break;
                    case 2:
                        CommonResource commonResource1 = new CommonResource();
                        for (int i = 1; i < 3; i++) {

                            Thread t = new Thread(new CountThread_1(commonResource1));
                            t.setName("Thread-synhro " + i);
                            t.start();
                        }
                        break;
                    default:

                        break;
                }
            }
        }


    }
}

class CommonResource {

    int x = 0;
}

class CountThread implements Runnable {

    CommonResource res;

    CountThread(CommonResource res) {
        this.res = res;
    }

    public void run() {
        res.x = 1;
        for (int i = 1; i < 2; i++) {
            System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
            res.x++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
}

class CountThread_1 implements Runnable {

    CommonResource res;

    CountThread_1(CommonResource res) {
        this.res = res;
    }

    public void run() {
        synchronized (res) {
            res.x = 1;
            for (int i = 1; i < 5; i++) {
                System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
                res.x++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}