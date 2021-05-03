package ua.khpi.oop.kogutenko13;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Random;

/**
 * The type Console auto.
 */
public class ConsoleAuto {

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
            ThreadTask th = new ThreadList.TaskPrintList(thread);
            th.start();
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            th.interrupt();

        }

        @Override
        public void run() {
            list.printList();
        }
    }

    static class TaskSerializationList extends ThreadTask {
        private static File file = new File("D:\\eclips-workspace\\kogutenko-oleksandr\\src\\ua\\khpi\\oop\\txt12-" + new Random().nextInt() % 20 + ".txt");///pathname
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
            ThreadTask th = new TaskPrintList(thread);
            th.start();
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            th.interrupt();

        }

        @Override
        public void run() {
            threadTask.list.serialization(3, file);
        }
    }

    static class TaskDeserializationList extends ThreadTask implements Runnable {
        private static File file = new File("D:\\eclips-workspace\\kogutenko-oleksandr\\src\\ua\\khpi\\oop\\txt12.txt");///pathname
        ThreadTask threadTask;

        TaskDeserializationList() {
        }

        TaskDeserializationList(HelperClassLink<Shops> list) {
            super(list);
            threadTask.list = list;
        }

        TaskDeserializationList(ThreadTask th) {
            super(th.getList());
            threadTask = th;
        }

        public static void deserialization(ThreadTask thread) {
            Thread th = new TaskDeserializationList(thread);
            th.start();
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            th.interrupt();

        }

        @Override
        public void run() {
            threadTask.getList().deserialization(3, file);
            System.out.println("size of list is " + threadTask.list.size());
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
            ThreadTask thread = new TaskFreshList(th);
            System.out.print("list before:\n");
            System.out.print("\n----------------------------------------------------------------------------------------------------------------------\n");
            thread.list.printList();
            System.out.print("----------------------------------------------------------------------------------------------------------------------\n");
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.interrupt();

        }

        @Override
        public void run() {
            try {
                thread.list = thread.list.fromArray(thread.list.bubbleSort(thread.list.toArray() , 2));
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("sort is bad");
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

        public static void fresh(ThreadTask th) {
            ThreadTask thread = new TaskFreshList(th);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.interrupt();

        }

        @Override
        public void run() {
            try {
                System.out.println( "----------------------------------------------------------------------------------------------------------------------");
                System.out.println( "Fresh product:\n----------------------------------------------------------------------------------------------------------------------\n" +
                        findFresh() +
                        "----------------------------------------------------------------------------------------------------------------------");
            } catch (Exception e) {
                e.printStackTrace();
                //System.err.println("sort is bad");
            }
        }
    }

    private static HelperClassLink<Shops> hlAuto = new HelperClassLink<>();
    private static ThreadTask thread = new ThreadTask(hlAuto);
    double deser, print, print1, sort, ser;
    /**
     * Start console.
     */
    public void startConsole() {
        System.out.println("Start auto...");
        try {
//deserialization
            deser = System.currentTimeMillis();
            TaskDeserializationList.deserialization(thread);
            deser = System.currentTimeMillis() - deser;
            hlAuto = thread.getList();
            System.out.println("Beginner container:\n" +
                    "----------------------------------------------------------------------------------------------------------------------");
            print = System.currentTimeMillis();
            TaskPrintList.print(thread);
            print = System.currentTimeMillis() - print;
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            int prod_id = thread.list.size() + 1;
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            java.util.Date date = new java.util.Date();
            String dateStr = dateFormat.format(date);
            String[] dateArr = dateStr.split("/");
            int currYear = Integer.parseInt(dateArr[0]),
                    currMon = Integer.parseInt(dateArr[1]),
                    currDay = Integer.parseInt(dateArr[2]);
            Shops newShop = new Shops(thread.list.size() + 1,
                    "prod_" + prod_id,
                    "l",
                    130,
                    133,
                    new Date(currDay, currMon, currYear),
                    new HashMap<String, String>() {{
                        put("color", "new_color");
                    }});
            System.out.println("new elem is\n" + newShop.toString());
            thread.list.add(newShop);
            System.out.println("Container after adding\n----------------------------------------------------------------------------------------------------------------------");
            print1 = System.currentTimeMillis();
            TaskPrintList.print(thread);
            print1 = System.currentTimeMillis() - print1;
            print = (print + print1) / 2;
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            System.out.println("remove the most expansive in price\nSorting by price");
            sort = System.currentTimeMillis();
            TaskSortList.sort(thread);
            sort = System.currentTimeMillis() - sort;
            hlAuto = thread.getList();
            System.out.println("Container after sorting\n----------------------------------------------------------------------------------------------------------------------");
            print1 = System.currentTimeMillis();
            TaskPrintList.print(thread);
            print1 = System.currentTimeMillis() - print1;
            print = (print + print1) / 2;
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            hlAuto.remove(hlAuto.size() - 1);
            System.out.println("Container after removing\n----------------------------------------------------------------------------------------------------------------------");
            print1 = System.currentTimeMillis();
            TaskPrintList.print(thread);
            print1 = System.currentTimeMillis() - print1;
            print = (print + print1) / 2;
            print1 = System.currentTimeMillis();
            TaskFreshList.fresh(thread);
            print1 = System.currentTimeMillis() - print1;
            print = (print + print1) / 2;
            System.out.println( "----------------------------------------------------------------------------------------------------------------------");
            System.out.println("this list was serializable: ");
            System.out.println( "----------------------------------------------------------------------------------------------------------------------");
            ser = System.currentTimeMillis();
            TaskSerializationList.serialization(thread);
            ser = System.currentTimeMillis() - ser;
            System.out.println( "----------------------------------------------------------------------------------------------------------------------");

//serialization
        } finally {
            System.out.println("average time of printing is        " + print + "ms\n" +
                               "average time of deserialization is " + deser + "ms\n" +
                               "average time of serialization is   " + ser   + "ms\n" +
                               "average time of sorting is         " + sort  + "ms\n"
                    );
            System.out.println("End auto...");
        }
    }


    /**
     * Find fresh string.
     *
     * @return the string
     */
    public static synchronized String findFresh() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date date = new java.util.Date();
        System.out.println("Today: " + dateFormat.format(date));
        String dateStr = dateFormat.format(date);
        String[] dateArr = dateStr.split("/");
        int currYear = Integer.parseInt(dateArr[0]),
                currMon = Integer.parseInt(dateArr[1]),
                currDay = Integer.parseInt(dateArr[2]);
        String str = "";
        for (Shops shop : thread.list) {
            int prodY = shop.getDate().getYear();
            int prodM = shop.getDate().getMonth();
            int prodD = shop.getDate().getDay();
            if (prodY == currYear) { // if year prod == curr year
                if (prodM == currMon) {
                    if (prodD <= currDay) {
                        str += shop.toString();
                    }
                } else if (currMon - prodM == 1 && prodD >= currDay) {
                    str += shop.toString();
                }
            }
        }
        System.out.println(str);
        return str;
    }
}
