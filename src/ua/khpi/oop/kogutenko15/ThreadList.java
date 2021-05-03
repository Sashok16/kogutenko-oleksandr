package ua.khpi.oop.kogutenko15;

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

        public static void print(ThreadTask thread) {
            ThreadTask th = new TaskPrintList(thread);
            th.start();
            th.interrupt();
        }

        @Override
        public void run() {
            list.printList();
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


        public static void serialization(ThreadTask thread) {
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

        public static void deserialization(ThreadTask thread) {
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

        public static void sort(ThreadTask th) {
            System.out.println("list before:\n");
            System.out.println("\n------------------------------------\n");
            thread.list.printList();
            System.out.println("\n------------------------------------\n");
            Shops[] shops = thread.list.toArray();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter field sorted (1 - name; 2 - price; 3 - date\n>>>");
                Pattern p = Pattern.compile("[123]");
                field = scanner.nextInt();
                Matcher m = p.matcher(field.toString());
                if (m.matches()) {
                    break;
                } else {
                    System.out.println("Enter info correctly!!!");
                }
            }
        }

        @Override
        public void run() {
            Shops[] shops = list.bubbleSort(list.toArray(), field);
            thread.list.list = thread.list.fromArray(shops);
        }
    }

}
