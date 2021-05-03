package ua.khpi.oop.kogutenko15;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Console auto.
 */
public class ConsoleAuto {
    private HelperClassLink<Shops> hlAuto = new HelperClassLink<>();
    /**
     * The Scanner.
     */
    Scanner scanner = new Scanner(System.in);

    /**
     * Start console.
     */
    public void startConsole() {
        System.out.println("Start auto...");
        try {
            deserializationTXT();
            System.out.println("Beginner container:\n" +
                    "----------------------------------------------------------------------------------------------------------------------");
            hlAuto.printList();
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            Shops newShop = new Shops(9,
                    "prod_9",
                    "l",
                    130,
                    133,
                    new Date(17, 2, 2021),
                    new HashMap<String, String>() {{
                        put("color", "new_color");
                        put("weight", "10");
                    }});
            System.out.println("new elem is\n" + newShop.toString());
            hlAuto.list.add(newShop);
            System.out.println("Container after adding\n----------------------------------------------------------------------------------------------------------------------");
            hlAuto.printList();
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            System.out.println("remove the most expansive in price");
            hlAuto = sort(hlAuto);
            System.out.println("Container after sorting\n----------------------------------------------------------------------------------------------------------------------");
            hlAuto.printList();
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            hlAuto.list.remove(hlAuto.list.size() - 1);
            System.out.println("Container after removing\n----------------------------------------------------------------------------------------------------------------------");
            hlAuto.printList();
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            System.out.println("Fresh product:\n" + findFresh() + "----------------------------------------------------------------------------------------------------------------------");

            serializationTXT();
        } finally {
            System.out.println("End auto...");
        }
    }

    private void serializationTXT() {
        File file = new File("D:\\eclips-workspace\\kogutenko-oleksandr\\src\\ua\\khpi\\oop\\txt10-" + new Random().nextInt() % 20 + ".txt");///pathname
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(file))) {
            System.out.println("size :" + hlAuto.list.size());
            for (Shops el : hlAuto.list) {
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
                hlAuto.list.add(shop);
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
    public HelperClassLink<Shops> sort(HelperClassLink<Shops> list) {
        System.out.println("Function sort\nlist before:\n");
        System.out.println("\n------------------------------------\n");
        list.printList();
        System.out.println("\n------------------------------------\n");
        Shops[] shops = new Shops[list.list.size()];
        for (int i = 0; i < shops.length; i++) {
            shops[i] = list.list.get(i);
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

    /**
     * Find fresh string.
     *
     * @return the string
     */
    public String findFresh() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date date = new java.util.Date();
        System.out.println(dateFormat.format(date));
        String dateStr = dateFormat.format(date);
        String[] dateArr = dateStr.split("/");
        int currYear = Integer.parseInt(dateArr[0]), currMon = Integer.parseInt(dateArr[1]), currDay = Integer.parseInt(dateArr[2]);
        String str = "";
        for (Shops shop : hlAuto.list) {
            int prodY = shop.getDate().getYear();
            int prodM = shop.getDate().getMonth();
            int prodD = shop.getDate().getDay();
            if (prodY == currYear) { // if year prod == curr year
                if (prodM == currMon) {
                    if (prodD >= currDay) {
                        str += shop.toString();
                    }
                } else if (currMon - prodM == 1 && prodD >= currDay) {
                    str += shop.toString();
                }
            } else if (prodY == currYear - 1 && prodM == 12 && prodD >= currDay) {
                str += shop.toString();
            }
        }
        return str;
    }
}
