package ua.khpi.oop.teststruct.Thread1;

import java.util.concurrent.atomic.AtomicInteger;

public class Test{

    public static void main(String[] args) throws InterruptedException {
// 1
//        RandomRunThread.example();
// 2
//        System.out.println("start interference");
        new InterferenceExample().example();
//        System.out.println("end interference\n");
// 3
//        SeriesRunExample.example();
    }
}

class SeriesRunExample extends Thread{
    private static int currentMax = 1;
    private int mainId;
    private final Object waitObject;

    public SeriesRunExample(int id, Object waitObject){
        this.mainId = id;
        this.waitObject = waitObject;
    }
    public static void example(){
        Object waitObject = new Object();
        for(int i = currentMax; i <= 5; i++){
            Thread thread = new SeriesRunExample(i, waitObject);
            thread.start();
        }
    }

    public void run(){
        try{
            System.out.println("Start run of thread: " + mainId);
            synchronized (waitObject){
                while(mainId > currentMax){
                    waitObject.wait();
                    //System.out.println("      from thread: " + mainId);
                }

                currentMax++;
                System.out.println("Hello from thread: " + mainId);
                waitObject.notifyAll();
            }
        } catch (InterruptedException e){e.printStackTrace();}
    }
}

class RandomRunThread extends Thread{
    public void run(){
        System.out.println(Thread.currentThread().getName());
    }

    public static void example(){
        for(int i = 0; i < 10; i++){
            Thread thread = new RandomRunThread();
            thread.start();
        }
    }
}

class StateObject{
    private int i;
    public synchronized void increment(){i++;}
    public int getI(){return i;}
}

class InterferenceThread extends Thread{
    private final InterferenceExample checker;
    //before 1
    //private static volatile int i;

    //after 1
      private static volatile int i;
      private static Object lock = new Object();

    //after 2
    //StateObject st = new StateObject();

    public InterferenceThread(InterferenceExample checker){this.checker = checker;}
    public void run(){
        while(!checker.stop()){
            //before 2
            increment();
            //after 2
            //st.increment();
        }
    }
    //before 1
    //public synchronized static void increment(){i++;}
    //after 1
    public void increment(){
        synchronized (lock){
            i++;
        }
    }
    public int getI(){
        //before 2
        return i;
        //after 2
        //return st.getI();
    }
}

class InterferenceExample{
    private static final int HundredMillion = 100_000_000;
    private AtomicInteger counter = new AtomicInteger();

    public boolean stop(){
        return counter.incrementAndGet() > HundredMillion;
    }

    public void example() throws InterruptedException{
        InterferenceThread thread1 = new InterferenceThread(this);
        InterferenceThread thread2 = new InterferenceThread(this);
        thread1.start();
        thread2.start();
        thread1.join();
        System.out.println("Expected: " + HundredMillion);
        System.out.println("Result:   " + thread1.getI());
    }
}