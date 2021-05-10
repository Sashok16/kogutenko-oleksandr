package ua.khpi.oop.kogutenko14;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Console auto.
 */
public class ConsoleAuto {

    static class TaskPrintList extends ThreadTask {
        TaskPrintList(HelperClassLink<Shops> list) {
            super(list);
        }

        TaskPrintList() {  }

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
            try {
                list.printList();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class TaskSerializationList extends ThreadTask {
        private static File file = new File("D:\\eclips-workspace\\kogutenko-oleksandr\\src\\ua\\khpi\\oop\\txt14-" + new Random().nextInt() % 20 + ".txt");///pathname
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

        public static void sort(ThreadTask th) throws InterruptedException {
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
    private static HelperClassLink<Shops> p_hlAuto = new HelperClassLink<>();
    private static ThreadTask thread = new ThreadTask(hlAuto);
    double deser, print, print1, sort, ser;
    double p_deser, p_print, p_print1, p_sort, p_ser;

    /**
     * Start console.
     */
    public void startConsole() throws InterruptedException {
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


            p_deser = System.currentTimeMillis();
            deserializationTXT();
            p_deser = System.currentTimeMillis() - p_deser;
            System.out.println("Beginner container:\n" +
                    "----------------------------------------------------------------------------------------------------------------------");
            p_print1 = System.currentTimeMillis();
            p_hlAuto.printList();
            p_print1 = System.currentTimeMillis() - p_print1;
            p_print = (p_print + p_print1) / 2;
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            Shops p_newShop = new Shops(9,
                    "prod_9",
                    "l",
                    130,
                    133,
                    new Date(17, 2, 2021),
                    new HashMap<String, String>() {{
                        put("color", "new_color");
                        put("weight", "10");
                    }});
            System.out.println("new elem is\n" + p_newShop.toString());

            p_hlAuto.add(newShop);

            System.out.println("Container after adding\n----------------------------------------------------------------------------------------------------------------------");
            p_print1 = System.currentTimeMillis();
            p_hlAuto.printList();
            p_print1 = System.currentTimeMillis() - p_print1;
            p_print = (p_print + p_print1) / 2;
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            System.out.println("remove the most expansive in price");
            p_sort = System.currentTimeMillis();
            p_hlAuto = sort(p_hlAuto);
            p_sort = System.currentTimeMillis() - p_sort;
            System.out.println("Container after sorting\n----------------------------------------------------------------------------------------------------------------------");
            p_print1 = System.currentTimeMillis();
            p_hlAuto.printList();
            p_print1 = System.currentTimeMillis() - p_print1;
            p_print = (p_print + p_print1) / 2;
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            p_hlAuto.remove(p_hlAuto.size() - 1);
            System.out.println("Container after removing\n----------------------------------------------------------------------------------------------------------------------");
            p_print1 = System.currentTimeMillis();
            p_hlAuto.printList();
            p_print1 = System.currentTimeMillis() - p_print1;
            p_print = (p_print + p_print1) / 2;
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            System.out.println("Fresh product:\n" + findFresh() + "----------------------------------------------------------------------------------------------------------------------");

            p_ser = System.currentTimeMillis();
            serializationTXT();
            p_ser = System.currentTimeMillis() - p_ser;
//serialization
        } finally {
            System.out.println(
                    "                               " + "thread\n" +
                    "average time of printing is        " + print + "ms\n" +
                    "average time of deserialization is " + deser + "ms\n" +
                    "average time of serialization is   " + ser   + "ms\n" +
                    "average time of sorting is         " + sort  + "ms\n"
            );
            System.out.println(
                    "                                       " + "no-thread\n" +
                            "average time of printing is        " + p_print + "ms\n" +
                            "average time of deserialization is " + p_deser + "ms\n" +
                            "average time of serialization is   " + p_ser   + "ms\n" +
                            "average time of sorting is         " + p_sort  + "ms\n"
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

    private void serializationTXT() {
        File file = new File("D:\\eclips-workspace\\kogutenko-oleksandr\\src\\ua\\khpi\\oop\\txt10-" + new Random().nextInt() % 20 + ".txt");///pathname
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(file))) {
            System.out.println("size :" + p_hlAuto.size());
            for (Shops el : p_hlAuto) {
                pw.write(el.toString());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializtion txt.
     */
    private void deserializationTXT() {
        File file = new File("D:\\eclips-workspace\\kogutenko-oleksandr\\src\\ua\\khpi\\oop\\txt10.txt");///pathname
        try {
            Pattern p;
            Matcher m;
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file)));
            String line, id = null,
                    name = null,
                    unit = null,
                    count = null,
                    price = null,
                    date = null,
                    description = null;
            while ((line = br.readLine()) != null) {
                if (line.contains("id:")) {
                    //regex
                    p = Pattern.compile("[0-9]+");
                    id = line.substring(4, line.indexOf(" | name:"));
                    m = p.matcher(id);
                    if (!m.matches()) {
                        id = "0";
                    }
                }
                if (line.contains("name:")) {
                    //regex
                    p = Pattern.compile("^[\\w]{3,15}$");
                    name = line.substring(line.indexOf("name: ") + 6, line.indexOf(" | unit:"));
                    m = p.matcher(name);
                    if (!m.matches()) {
                        name = "prod";
                    }
                }
                if (line.contains("unit:")) {
                    //regex
                    p = Pattern.compile("kg|l|kg/l");
                    unit = line.substring(line.indexOf("unit:") + 6, line.indexOf(" | count: "));
                    m = p.matcher(unit);
                    if (!m.matches()) {
                        unit = "obj";
                    }
                }
                if (line.contains("count:")) {
                    //regex
                    p = Pattern.compile("[0-9]+");
                    count = line.substring(line.indexOf("count:") + 7, line.indexOf(" | price: "));
                    m = p.matcher(count);
                    if (!m.matches()) {
                        count = "0";
                    }
                }
                if (line.contains("price")) {
                    //regex
                    p = Pattern.compile("[0-9]+");
                    price = line.substring(line.indexOf("price:") + 7, line.indexOf(" | date: "));
                    m = p.matcher(price);
                    if (!m.matches()) {
                        price = "0";
                    }

                }
                if (line.contains("date:")) {
                    //regex

                    p = Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
                    date = line.substring(line.indexOf("date:") + 6, line.indexOf(" | description: "));
                    m = p.matcher(date);
                    if (!m.matches()) {
                        date = "01/01/2021";
                    }
                }
                if (line.contains("description:")) {
                    //regex
                    description = line.substring(line.indexOf("description:") + 13, line.length() - 1);
                }

                Shops shop = new Shops();
                shop.setId(Integer.parseInt(id));
                shop.setCount(Integer.parseInt(count));
                shop.setName(name);
                shop.setDate(date);
                shop.setUnit(unit);
                shop.setPrice(Integer.parseInt(price));
                shop.setDescription(description);
                p_hlAuto.add(shop);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //catch (ClassNotFoundException e) {e.printStackTrace();}
    }

    /**
     * Sort helper class link.
     *
     * @param list the list
     * @return the helper class link
     */
    public HelperClassLink<Shops> sort(HelperClassLink<Shops> list) throws InterruptedException {
        System.out.println("Function sort\nlist before:\n");
        System.out.println("\n------------------------------------\n");
        list.printList();
        System.out.println("\n------------------------------------\n");
        Shops[] shops = new Shops[list.size()];
        for (int i = 0; i < shops.length; i++) {
            shops[i] = list.get(i);
        }
        bubbleSort(shops, 2);
        return new HelperClassLink<>(shops);
    }

    private void bubbleSort(Shops[] array, int field) {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 1; i < array.length; i++) {
                if (compare(array[i], array[i - 1], field)) {
                    swap(array, i, i - 1);
                    sorted = false;
                }
            }
        }
    }

    private boolean compare(Shops a, Shops b, int field) {
        switch (field) {
            case 1:
                return a.getName().compareTo(b.getName()) >= 0;
            case 2:
                return a.getPrice() < b.getPrice();
            case 3:
                return (a.getDate().getYear() > b.getDate().getYear())
                        || (a.getDate().getYear() == b.getDate().getYear() && a.getDate().getMonth() > b.getDate().getMonth())
                        || (a.getDate().getYear() == b.getDate().getYear() && a.getDate().getMonth() == b.getDate().getMonth()
                        && a.getDate().getDay() > b.getDate().getDay());

        }
        return false;
    }

    private void swap(Shops[] array, int ind1, int ind2) {
        Shops tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }
}
