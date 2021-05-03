package ua.khpi.oop.teststruct.Thread8;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task {
    static class TaskThread extends Thread {
        protected int[] array = new int[5];
        TaskThread(int[] array){
            this.array = array;
        }
        public TaskThread() { }
        public void show(){
            System.out.println("TaskThread: array length is " + array.length);
            for(int i = 0; i < array.length; i++ ) {
                System.out.print(array[i] + " ");
            }
        }
        public int[] getArray(){
            return array;
        }
    }

    static class TaskInnerArr extends TaskThread{
        TaskInnerArr(int[] array) {
            super(array);
        }

        TaskInnerArr() { }

        TaskInnerArr(TaskThread thread) {
            array = thread.array;
        }

        public static void InnerArr(TaskThread th) {
            TaskThread thread = new TaskInnerArr(th);
            //thread.setName("InnerArr");
            thread.start();
            System.out.println(thread.getName() + " is " + thread.getState());
            thread.interrupt();
            System.out.println();
            System.out.println(thread.getName() + " is " + thread.getState());

        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            Random random = new Random();
            System.out.println(Thread.currentThread().getName() + ": array length is " + array.length);
            for(int i = 0; i < array.length; i++){
                array[i] = random.nextInt() % 100 + 1;
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }
    }

    static class TaskSumArr extends TaskThread{
        ExecutorService es = Executors.newCachedThreadPool();
        TaskSumArr(int[] array) {
            super(array);
        }

        TaskSumArr() { }

        TaskSumArr(TaskThread thread) {
            array = thread.array;
        }


        public static void sum(TaskThread thread){
            TaskThread thread1 = new TaskSumArr(thread);
            //1thread.setName("Sum-Arr");
            thread1.start();
            System.out.println(thread1.getName() + " is " + thread.getState());
            thread1.interrupt();
            System.out.println(thread1.getName() + " is " + thread.getState());

        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            int sum = 0;
            show();
            for (int i = 0; i < array.length; i++){
                sum += array[i];
                System.out.println("array[" + i + "] = " + array[i]);
                System.out.println("sum: " + sum);
            }
            System.out.println("sum is " + sum);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        int[] arr = new int[0];
        TaskThread thread = new TaskThread();
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(thread);
        while(input != 4){
            input = scanner.nextInt();
            switch (input){
                case 1:
                    //System.out.println(Thread.currentThread().getName());
                    TaskInnerArr.InnerArr(thread);
                    break;
                case 2:
                    //System.out.println(Thread.currentThread().getName());
                    TaskSumArr.sum(thread);
                    break;
                case 3:
                    System.out.print("Main: ");
                    for(int i = 0; i < arr.length; i++){
                        System.out.print(arr[i] + " ");
                    }
                    break;
                default:
                    break;
            }
        }

    }
}
