package ua.khpi.oop.kogutenko15;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Interactive console.
 */
public class InteractiveConsole {
    Pattern p;
    Matcher m;
    private String nickname;
    boolean check = true;
    String input;
    Scanner scanner = new Scanner(System.in);
    HelperClassLink<Shops> list = new HelperClassLink<>();
    ThreadTask th_list = new ThreadTask(list);

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void startConsole() {
        try {
            System.out.print("Input your nickname: ");
            p = Pattern.compile("[\\w]{2,8}");
            String nick = scanner.nextLine();
            m = p.matcher(nick);
            if (m.matches()) {
                setNickname(nick);
            } else {
                setNickname("NickName");
            }

            while (check) {
                System.out.println(
                                "1 / input   \t-\t input from file\n" +
                                "2 / show    \t-\t show information about shops\n" +
                                "3 / add     \t-\t add one shop\n" +
                                "4 / remove  \t-\t remove one shop\n" +
                                "5           \t-\t show list without thread" +
                                "8 / fresh   \t-\t find fresh product\n" +
                                "9 / sort    \t-\t sort linked list by fields\n" +
                                "0 / exit    \t-\t exit and save data\n");
                System.out.print(nickname + "@" + nickname + "$: ");

                input = scanner.nextLine();
                list = th_list.getList();
                switch (input) {
                    case "1":
                        //inputting
                        ThreadList.TaskDeserializationList.deserialization(th_list);
                        break;
                    case "2":
                        //printing
                        ThreadList.TaskPrintList.print(th_list);
                        break;
                    case "3":
                        //adding
                        Shops shop = new Shops();
                        shop.add();
                        th_list.list.list.add(shop);
                        break;
                    case "4":
                        //removing
                        th_list.list.printList();
                        Scanner sc = new Scanner(System.in);
                        System.out.print("Enter number of id: ");
                        //regex
                        int id = sc.nextInt();
                        //
                        if (id < 0 || id > th_list.list.list.size()) {
                            throw new Exception("out of range!!!!!");
                        } else {
                            Shops shopR = th_list.list.list.remove(id);
                            System.out.println(shopR.toString());
                        }
                        break;
                    case "5":
                        list.printList();
                        break;
                    case "0":
                        //exiting
                        ThreadList.TaskSerializationList.serialization(th_list);
                        check = false;
                        break;
                    case "9":
                        //sorting
                        ThreadList.TaskSortList.sort(th_list);
                        break;
                    case "8":
                        //freshening
                        System.out.println(findFresh());
                        break;
                    default:
                        System.out.println("(" + input + ") I don't know this command :(");
                        break;
                }
            }
            System.out.println("GOOD BEY!!!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    /**
     * Deserializtion txt.
     */
    public void deserializationTXT() {
        File file = ConsoleFile.MenuFillIn();///pathname
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file)));
            System.out.println("Starting deserialization!!!");
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
                    p = Pattern.compile("[0-9^\\s]+");
                    id = line.substring(4, line.indexOf(" | name:")).trim();
                    m = p.matcher(id);
                    if (!m.matches()) {
                        id = "0";
                    }
                    System.out.println("id is " + id);
                }
                if (line.contains("name:")) {
                    //regex
                    p = Pattern.compile("^[\\w^\\s]{3,15}$");
                    name = line.substring(line.indexOf("name: ") + 6, line.indexOf(" | unit:")).trim();
                    m = p.matcher(name);
                    if (!m.matches()) {
                        name = "prod";
                    }
                    System.out.println("name is " + name);
                }
                if (line.contains("unit:")) {
                    //regex
                    p = Pattern.compile("kg|l|kg/l");
                    unit = line.substring(line.indexOf("unit:") + 6, line.indexOf(" | count: ")).trim();
                    m = p.matcher(unit);
                    if (!m.matches()) {
                        unit = "obj";
                    }
                    System.out.println("unit is " + unit);
                }
                if (line.contains("count:")) {
                    //regex
                    p = Pattern.compile("[0-9[^\\n\\t\\f\\r]]+");
                    count = line.substring(line.indexOf("count:") + 7, line.indexOf(" | price: ")).trim();
                    m = p.matcher(count);
                    if (!m.matches()) {
                        count = "0";
                    }
                    System.out.println("count is " + count);
                }
                if (line.contains("price")) {
                    //regex
                    p = Pattern.compile("[0-9[^\\n\\t\\f\\r]]+");
                    price = line.substring(line.indexOf("price:") + 7, line.indexOf(" | date: ")).trim();
                    m = p.matcher(price);
                    if (!m.matches()) {
                        price = "0";
                    }
                    System.out.println("price is " + price);
                }
                if (line.contains("date:")) {
                    //regex
                    p = Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
                    date = line.substring(line.indexOf("date:") + 6, line.indexOf(" | description: "));
                    m = p.matcher(date);
                    if (!m.matches()) {
                        date = "01/01/2021";
                    }
                    System.out.println("date is " + date);
                }
                if (line.contains("description:")) {
                    //regex
                    description = line.substring(line.indexOf("description:") + 13, line.length() - 1);
                    System.out.println("description is " + description);
                }

                Shops shop = new Shops();
                shop.setId(Integer.parseInt(id));
                shop.setCount(Integer.parseInt(count));
                shop.setName(name);
                shop.setDate(date);
                shop.setUnit(unit);
                shop.setPrice(Integer.parseInt(price));
                shop.setDescription(description);
                //System.out.println(shop.toString());
                //results.add(shop);
            }
            System.out.println("Finish deserialization!!!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sort helper class link.
     *
     * @param list the list
     * @return the helper class link
     */
    public HelperClassLink<Shops> sort(HelperClassLink<Shops> list) {
        System.out.println("list before:\n");
        System.out.println("\n------------------------------------\n");
        list.printList();
        System.out.println("\n------------------------------------\n");
        Shops[] shops = list.toArray();
        Integer field;
        while (true) {
            System.out.print("Enter field sorted (1 - name; 2 - price; 3 - date\n>>>");
            p = Pattern.compile("[123]");
            field = scanner.nextInt();
            m = p.matcher(field.toString());
            if (m.matches()) {
                break;
            } else {
                System.out.println("Enter info correctly!!!");
            }
        }
        bubbleSort(shops, field);
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
        java.util.Date date = new Date();
        System.out.println(dateFormat.format(date));
        String dateStr = dateFormat.format(date);
        String[] dateArr = dateStr.split("/");
        int currYear = Integer.parseInt(dateArr[0]), currMon = Integer.parseInt(dateArr[1]), currDay = Integer.parseInt(dateArr[2]);
        String str = "";
        HelperClassLink<Shops> list = this.list;
        for (Shops shop : list.list) {
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

