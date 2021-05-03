package ua.khpi.oop.teststruct.Thread5;

import java.util.Random;

class Egg extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                // Приостанавливаем поток
                sleep(ChickenEgg.getTimeSleep());
                System.out.print("Яйцо-" + getName() + "|");
            } catch (InterruptedException e) {
            }
        }
    }
}

public class ChickenEgg {
    public static int getTimeSleep() {
        final Random random = new Random();
        int tm = random.nextInt(900) + 100;
        if (tm < 10)
            tm *= 100;
        else if (tm < 100)
            tm *= 10;
        return tm;
    }

    public static void main(String[] args) {
        Egg egg = new Egg(); // Создание потока
        System.out.println(
                "Начинаем спор : кто появился первым ?");
        egg.setName("Egg");
        egg.start(); // Запуск потока
        for (int i = 0; i < 5; i++) {
            try {
                // Приостанавливаем поток
                Thread.sleep(ChickenEgg.getTimeSleep());
                System.out.print("Курица|");
            } catch (InterruptedException e) {
            }
        }
        if (egg.isAlive()) {
            // Cказало ли яйцо последнее слово?
            try {
                // Ждем, пока яйцо закончит высказываться
                egg.join();
            } catch (InterruptedException e) {
            }

            System.out.println("\nПервым появилось яйцо !!!");
        } else {
            //если оппонент уже закончил высказываться
            System.out.println("\nПервой появилась курица !!!");
        }
        System.out.println("Спор закончен");
    }
}