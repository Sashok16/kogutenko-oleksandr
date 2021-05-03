package ua.khpi.oop.teststruct.Thread7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
    private static int MONITORING_FREQUENCY = 5;
    private static int NUMBER_OF_TASKS = 3;

    public static void main(String[] args) {
        List<ThreadMonitor> threadMonitors = initThreadMonitors();

        threadMonitors.forEach(m -> m.printState());
        threadMonitors.forEach(m -> m.startThread());

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> threadMonitors.forEach(m -> m.restartIfTerminated()), MONITORING_FREQUENCY, MONITORING_FREQUENCY, TimeUnit.SECONDS);
    }

    private static List<ThreadMonitor> initThreadMonitors() {
        List<ThreadMonitor> threadMonitors = new ArrayList<>();

        for (int i = 1; i <= NUMBER_OF_TASKS; i++) {
            DummyRunnable runnable = new DummyRunnable(i);
            ThreadMonitor threadMonitor = new ThreadMonitor(runnable);
            threadMonitors.add(threadMonitor);
        }

        return threadMonitors;
    }
}

class ThreadMonitor {

    private Thread thread;
    private DummyRunnable runnable;

    public ThreadMonitor( DummyRunnable runnable) {
        this.runnable = runnable;
        this.thread = new Thread(runnable);
    }

    public boolean startThread() {
        boolean isStarCalled = false;
        if(Thread.State.NEW.equals(thread.getState())) {
            thread.start();
            isStarCalled = true;
        }
        return isStarCalled;
    }

    public void printState() {
        System.out.println( thread.toString() + " is in state : " + thread.getState());
    }

    public void restartIfTerminated() {
        printState();
        if(Thread.State.TERMINATED.equals(thread.getState())) {
            thread = new Thread(runnable);
            thread.start();
        }
    }
}

class DummyRunnable implements Runnable {

    private int id;

    public DummyRunnable(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Runnable " + id + " started in thread: " + Thread.currentThread());
        dummyWork();
        System.out.println("Runnable " + id + " done");
    }

    private void dummyWork() {
        int sleep = 10000;
        if (id == 3) {
            sleep = 1000;
        }
        try {
            Thread.sleep(sleep);
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}