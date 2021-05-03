package ua.khpi.oop.kogutenko14;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThreadList {
    static class TaskPrintList extends ThreadTask {
        TaskPrintList(HelperClassLink<Shops> list) {
            super(list);
        }

        TaskPrintList() {
        }

        TaskPrintList(ThreadTask thread) {
            super(thread.getList());
        }

        public static void print(ThreadTask thread) throws InterruptedException {
            ThreadTask th = new TaskPrintList(thread);
            th.start();
            th.join();
            th.interrupt();
        }

        @Override
        public void run() {
            try {
                list.printList();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class TaskSerializationList extends ThreadTask {
        private static File file;
        private static Integer answ;
        ThreadTask threadTask;

        TaskSerializationList() {
        }

        TaskSerializationList(HelperClassLink<Shops> list) {
            super(list);
            threadTask.list = list;
        }

        TaskSerializationList(ThreadTask thread) {
            super(thread.getList());
            threadTask = thread;
        }


        public static void serialization(ThreadTask thread) throws InterruptedException {
            System.out.print("What save do you want? (1 - .txt; 2 - .bin; 3 - .xml)\n>>> ");
            Integer answ;
            Scanner scanner = new Scanner(System.in);
            while (true) {
                Pattern p = Pattern.compile("[123]");
                answ = scanner.nextInt();
                Matcher m = p.matcher(answ.toString());
                if (m.matches()) {
                    file = ConsoleFile.MenuFillOut();///pathname
                    ThreadTask th = new TaskPrintList(thread);
                    th.start();
                    th.join();
                    th.interrupt();
                } else {
                    System.out.println("Enter info correctly!!!");
                }
            }
        }

        @Override
        public void run() {
            threadTask.list.serialization(answ, file);
        }
    }

    static class TaskDeserializationList extends ThreadTask implements Runnable {
        private static File file;
        private static Integer answ;
        ThreadTask threadTask;
        TaskDeserializationList() { }

        TaskDeserializationList(HelperClassLink<Shops> list) {
            super(list);
            threadTask.list = list;
        }

        TaskDeserializationList(ThreadTask th) {
            super(th.getList());
            threadTask = th;
        }

        public static void deserialization(ThreadTask thread) throws InterruptedException {
            System.out.print("what deserialization do you want?\n(1 - bin, 2 - xml, 3 - txt)\n>>> ");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("regex verification");
                Pattern p = Pattern.compile("[123]");
                answ = scanner.nextInt();
                Matcher m = p.matcher(answ.toString());
                if (m.matches()) {
                    file = ConsoleFile.MenuFillIn();///pathname
                    Thread th = new TaskDeserializationList(thread);
                    th.start();
                    th.join();
                    th.interrupt();
                    break;
                } else {
                    System.out.println("Enter info correctly!!!");
                }
            }
        }

        @Override
        public void run() {
            threadTask.getList().deserialization(answ, file);
        }
    }

    static class TaskSortList extends ThreadTask {
        private static ThreadTask thread;
        private static Integer field;
        TaskSortList() {
        }

        TaskSortList(HelperClassLink<Shops> list) {
            super(list);
            thread.list = list;
        }

        TaskSortList(ThreadTask thread) {
            super(thread.getList());
            this.thread = thread;
        }

        public static void sort(ThreadTask th) throws InterruptedException {
            ThreadTask thread = new TaskSortList(th);
            thread.start();
            thread.join();
            thread.interrupt();
        }

        @Override
        public void run() {
            System.out.println("start run sort");
            try {
                thread.list.printList();
                System.out.println("-------------------------------------------------------");
                thread.list = thread.list.fromArray(thread.list.bubbleSort(thread.list.toArray() , 3));
                thread.list.printList();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class TaskFreshList extends ThreadTask {
        private static ThreadTask thread;
        private static Integer field;
        TaskFreshList() {
        }

        TaskFreshList(HelperClassLink<Shops> list) {
            super(list);
            thread.list = list;
        }

        TaskFreshList(ThreadTask thread) {
            super(thread.getList());
            this.thread = thread;
        }

        public static void fresh(ThreadTask th) throws InterruptedException {
            ThreadTask thread = new TaskFreshList(th);
            thread.start();
            thread.join();
            thread.interrupt();
        }

        @Override
        public void run() {
            System.out.println(thread.list.findFresh(thread.getList()));
        }
    }

}
