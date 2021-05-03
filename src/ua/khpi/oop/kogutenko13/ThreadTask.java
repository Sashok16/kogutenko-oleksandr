package ua.khpi.oop.kogutenko13;

import java.util.concurrent.Callable;
import java.util.function.Function;

public class ThreadTask extends Thread{
    protected HelperClassLink<Shops> list;

    ThreadTask() {
        list = new HelperClassLink<>();
    }

    ThreadTask(HelperClassLink<Shops> list) {
        this.list = list;
    }

    public HelperClassLink<Shops> getList(){
        return list;
    }
}
