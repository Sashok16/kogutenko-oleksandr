package ua.khpi.oop.teststruct.Thread6;

public class Trade
{

    public static void main(String[] args)
    {
        Store store = new Store();
        Producer  producer = new Producer(store);
        Consumer  consumer = new Consumer(store);

//		new Thread(producer).start();
//		new Thread(consumer).start();

        Thread  tp = new Thread(producer);
        Thread  tc = new Thread(consumer);

        tp.setDaemon(false);
        tc.setDaemon(false);

        tp.start();
        tc.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}

        System.out.println("\nГлавный поток завершен\n");
        System.exit(0);
    }
}

// Класс Магазин, хранящий произведенные товары
class Store {
    private int product = 0;

    public synchronized void get() {
        while (product < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        product--;
        System.out.println("Покупатель купил 1 товар");
        System.out.println("Товаров на складе: " + product);
        notify();
    }

    public synchronized void put() {
        while (product >= 3) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        product++;
        System.out.println("\tПроизводитель добавил 1 товар");
        System.out.println("\tТоваров на складе: " + product);
        notify();
    }
}

// класс Производитель
class Producer implements Runnable {

    Store store;

    Producer(Store store) {
        this.store = store;
    }

    public void run() {
        for (int i = 1; i < 5; i++) {
            store.put();
        }
    }
}

// Класс Потребитель
class Consumer implements Runnable {

    Store store;

    Consumer(Store store) {
        this.store = store;
    }

    public void run() {
        for (int i = 1; i < 5; i++) {
            store.get();
        }
    }
}