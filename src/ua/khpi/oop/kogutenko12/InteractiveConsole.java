package ua.khpi.oop.kogutenko12;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Interactive console.
 */
public class InteractiveConsole {
    Pattern p;
    Matcher m;
    private String nickname;
    /**
     * The Answer deserialization.
     */
    int answerDeserialization = 0;
    /**
     * The Check.
     */
    boolean check = true;
    /**
     * The Input.
     */
    String input;
    /**
     * The Helper l.
     */
    HelperClassLink<Shops> helperL = new HelperClassLink<>();
    /**
     * The Scanner.
     */
    Scanner scanner = new Scanner(System.in);

    /**
     * Gets nikname.
     *
     * @return the nikname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets nikname.
     *
     * @param nickname the nikname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Start console.
     */
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
                                "5 / switch  \t-\t switch to another list\n" +
                                "8 / fresh   \t-\t find fresh product\n" +
                                "9 / sort    \t-\t sort linked list by fields\n" +
                                "0 / exit    \t-\t exit and save data\n");
                System.out.print(nickname + "@" + nickname + ": ");
                //regex
                input = scanner.nextLine();
                //
                switch (input) {
                    case "\n": {
                        System.out.print(nickname + "@" + nickname + ": ");
                        break;
                    }
                    case "1": {
                        System.out.println("LinkedList\n");
                        System.out.print("what deserialization do you want?\n(1 - bin, 2 - xml, 3 - txt)\n>>> ");
                        answerDeserialization = scanner.nextInt();
                        switch (answerDeserialization) {
                            case 1: {
                                helperL.deserializationBIN();
                                break;
                            }
                            case 2: {
                                helperL.deserializationXML();
                                break;
                            }
                            case 3: {
                                deserializationTXT();
                                break;
                            }
                            default: {
                                System.out.println("don't have this method ;((");
                                break;
                            }
                        }
                        break;
                    }
                    case "2": {
                        helperL.printList();
                        break;
                    }
                    case "3": {
                        Shops shop = new Shops();
                        shop.add();
                        helperL.add(shop);
                        break;
                    }
                    case "4": {
                        helperL.printList();
                        Scanner sc = new Scanner(System.in);
                        System.out.print("Enter number of id: ");
                        //regex
                        int id = sc.nextInt();
                        //
                        if (id < 0 || id > helperL.size()) {
                            throw new Exception("out of range!!!!!");
                        } else if (!helperL.remove(id)) {
                            System.out.println("NOT FOUND");
                        } else {
                            helperL.printList();
                        }
                        break;
                    }
                    case "0": {
                        System.out.print("What save do you want? (1 - .txt; 2 - .bin; 3 - .xml)\n>>> ");
                        Integer answ;
                        //regex
                        while (true) {
                            p = Pattern.compile("[123]");
                            answ = scanner.nextInt();
                            m = p.matcher(answ.toString());
                            if (m.matches()) {
                                break;
                            } else {
                                System.out.println("Enter info correctly!!!");
                            }
                        }

                        //
                        switch (answ) {
                            case 1: {
                                serializationTXT();
                                break;
                            }
                            case 2: {
                                helperL.serializationBIN();
                                break;
                            }
                            case 3: {
                                helperL.serializationXML();
                                break;
                            }
                            default: {
                                System.out.println("We dont save your array (");
                                break;
                            }
                        }
                        check = false;
                        break;
                    }
                    case "9": {
                        System.out.println("Entrance to 9(sorting)");
                        helperL = sort(helperL);
                        System.out.println("list after:\n");
                        System.out.println("\n------------------------------------\n");
                        helperL.printList();
                        System.out.println("\n------------------------------------\n");
                    }
                    case "8": {
                        System.out.println(findFresh());
                        break;
                    }
                    default: {
                        System.out.println("(" + input + ") I don't know this command :(");
                        break;
                    }
                }
            }
            System.out.println("GOOD BEY!!!");
        } catch (Exception e) {
            System.out.println(e);
            check = false;
        }
    }

    /**
     * Serialization txt.
     */
    public void serializationTXT() {
        File file = ConsoleFile.MenuFillOut(".txt");///pathname
        try (PrintWriter pw = new PrintWriter(file.getName())) {
            System.out.println("size :" + helperL.size());
            for (Shops el : helperL) {
                pw.write(el.toString());
                System.out.print(el.toString());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializtion txt.
     */
    public void deserializationTXT() {
        File file = ConsoleFile.MenuFillIn(".txt");///pathname
        try {
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
                helperL.add(shop);
            }
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
        Shops[] shops = new Shops[list.size()];
        for (int i = 0; i < shops.length; i++) {
            shops[i] = list.get(i);
        }
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

        //
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

    public String findFresh() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date date = new Date();
        System.out.println(dateFormat.format(date));
        String dateStr = dateFormat.format(date);
        String[] dateArr = dateStr.split("/");
        int currYear = Integer.parseInt(dateArr[0]), currMon = Integer.parseInt(dateArr[1]), currDay = Integer.parseInt(dateArr[2]);
        String str = "";
        for (Shops shop : helperL) {
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

