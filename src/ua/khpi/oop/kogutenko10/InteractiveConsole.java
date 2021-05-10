package ua.khpi.oop.kogutenko10;

import java.io.*;
import java.util.Scanner;

/**
 * The type Interactive console.
 */
public class InteractiveConsole
{
    private String  nikname;
    /**
     * The Answer deserialization.
     */
    int answerDeserialization = 0;
    /**
     * The Check.
     */
    boolean check = true, /**
    * The Check choicelist.
    */
    checkChoicelist = false, /**
    * The List.
    */
    list = false;
    /**
     * The Input.
     */
    String input;
    /**
     * The Helper.
     */
    HelperClass helper = new HelperClass();
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
    public String getNikname() {
        return nikname;
    }

    /**
     * Sets nikname.
     *
     * @param nikname the nikname
     */
    public void setNikname(String nikname) {
        this.nikname = nikname;
    }

    /**
     * Start console.
     */
    public void startConsole() {
               try {
            System.out.print("Input your nikname: ");
            setNikname(scanner.nextLine());
            while (check) {
                if(list)
                {
                    System.out.println("ArrayList\n");
                }
                else
                {
                    System.out.println("LinkedList\n");
                }
                if(list){
                    System.out.println(
                            "1 / input   \t-\t input from file\n" +
                            "2 / show    \t-\t show information about shops\n" +
                            "3 / add     \t-\t add one shop\n" +
                            "4 / remove  \t-\t remove one shop\n" +
                            "5 / switch  \t-\t switch to another list\n" +
                            "0 / exit    \t-\t exit and save data\n"
                    );
                } else {
                    System.out.println(
                            "1 / input   \t-\t input from file\n" +
                            "2 / show    \t-\t show information about shops\n" +
                            "3 / add     \t-\t add one shop\n" +
                            "4 / remove  \t-\t remove one shop\n" +
                            "5 / switch  \t-\t switch to another list\n" +
                            "9 / sort    \t-\t sort linked list by fields\n" +
                            "0 / exit    \t-\t exit and save data\n"
                    );
                }

                System.out.print(nikname + "@" + nikname + ": ");
                //scanner.nextLine();
                input = scanner.nextLine();
                switch (input) {
                    case " ": {
                        break;
                    }
                    case "input": {
                        if(list) {
                            helper.deserializtionXML();
                        }
                        else {
                            System.out.println("LinkedList\n");
                            System.out.print("what deserialization do you want?\n(1 - bin, 2 - xml, 3 - txt)\n>>> ");
                            answerDeserialization = scanner.nextInt();
                            switch (answerDeserialization)
                            {
                                case 1 :
                                {
                                    helperL.deserializationBIN();
                                    break;
                                }
                                case 2 :
                                {
                                    helperL.deserializationXML();
                                    break;
                                }
                                case 3 :
                                {
                                    deserializationTXT();
                                    break;
                                }
                                default:
                                {
                                    System.out.println("don't have this method ;((");
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case "1": {
                        if(list) {
                            helper.deserializtionXML();
                        }
                        else {
                            System.out.println("LinkedList\n");
                            System.out.print("what deserialization do you want?\n(1 - bin, 2 - xml, 3 - txt)\n>>> ");
                            answerDeserialization = scanner.nextInt();
                            switch (answerDeserialization)
                            {
                                case 1 :
                                {
                                    helperL.deserializationBIN();
                                    break;
                                }
                                case 2 :
                                {
                                    helperL.deserializationXML();
                                    break;
                                }
                                case 3 :
                                {
                                    deserializationTXT();
                                    break;
                                }
                                default:
                                {
                                    System.out.println("don't have this method ;((");
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case "show": {
                        if(list)
                            helper.printSaves();
                        else
                            helperL.printList();
                        break;
                    }
                    case "2": {
                        if(list)
                            helper.printSaves();
                        else
                            helperL.printList();
                        break;
                    }
                    case "add": {
                        if(list)
                            helper.add();
                        else
                        {
                            Shops shop = new Shops();
                            shop.add();
                            helperL.add(shop);
                        }
                        break;
                    }
                    case "3": {
                        if(list)
                            helper.add();
                        else
                        {
                            Shops shop = new Shops();
                            shop.add();
                            helperL.add(shop);
                        }
                        break;
                    }
                    case "remove": {
                        if(list)
                            helper.remove();
                        else
                        {
                            helperL.printList();
                            Scanner sc = new Scanner(System.in);
                            System.out.print("Enter number of id: ");
                            int id = sc.nextInt();
                            if (id < 0 || id > helperL.size()) {
                                throw new Exception("out of range!!!!!");
                            } else if(!helperL.removeElement(helperL.get(id)))
                            {
                                System.out.println("NOT FOUND");
                            } else
                            {
                                helperL.printList();
                            }
                        }
                        break;
                    }
                    case "4": {
                        if(list)
                            helper.remove();
                        else
                        {
                            helperL.printList();
                            Scanner sc = new Scanner(System.in);
                            System.out.print("Enter number of id: ");
                            int id = sc.nextInt();
                            if (id < 0 || id > helperL.size()) {
                                throw new Exception("out of range!!!!!");
                            } else if(!helperL.remove(id))
                            {
                                System.out.println("NOT FOUND");
                            } else
                            {
                                helperL.printList();
                            }
                        }
                        break;
                    }
                    case "switch": {
                        if (checkChoicelist == false) checkChoicelist = !checkChoicelist;
                        boolean checkListAnsw = true;
                        while (checkListAnsw)
                        {
                            int answer = 0;
                            System.out.print(
                                    "array list  - 1\n" +
                                    "linked list - 2\n" +
                                    "what list do you want to use? (1 or 2)\n>>> ");
                            Scanner scanner1 = new Scanner(System.in);
                            answer = scanner1.nextInt();
                            if(answer == 1)
                            {
                                list = true;
                                checkListAnsw = false;
                            }
                            else if (answer == 2)
                            {
                                list = false;
                                checkListAnsw = false;
                            }
                            else
                            {
                                System.out.println("list do not find ((((" +
                                                   "\ntry again!!!");
                            }
                        }

                        break;
                    }
                    case "5": {
                        if (checkChoicelist == false) checkChoicelist = !checkChoicelist;
                        boolean checkListAnsw = true;
                        while (checkListAnsw)
                        {
                            System.out.print(
                                    "array list  - 1\n" +
                                    "linked list - 2\n" +
                                    "what list do you want to use? (1 or 2)\n>>> ");
                            int answer = 0;
                            Scanner scanner1 = new Scanner(System.in);
                            answer = scanner1.nextInt();
                            if(answer == 1)
                            {
                                list = true;
                                checkListAnsw = false;
                            }
                            else if (answer == 2)
                            {
                                list = false;
                                checkListAnsw = false;
                            }
                            else
                            {
                                System.out.println("list do not find ((((" +
                                                   "\ntry again!!!");
                            }
                        }

                        break;
                    }
                    case "exit": {
                        if(list) {
                            helper.serialization();
                        }
                        else {
                            switch (answerDeserialization)
                            {
                                case 0:
                                {
                                   break;
                                }
                                case 1 :
                                {
                                    helperL.serializationBIN();
                                    break;
                                }
                                case 2 :
                                {
                                    helperL.serializationXML();
                                    break;
                                }
                                case 3 :
                                {
                                    serializationTXT();
                                    break;
                                }
                            }
                        }
                        check = false;
                        break;
                    }
                    case "0": {
                        if(list) {
                            helper.serialization();
                        }
                        else {
                            //System.out.println("saved linkedlist");
                            System.out.print("What save do you want? (1 - .txt; 2 - .bin; 3 - .xml)\n>>> ");
                            int answ = scanner.nextInt();
                            switch (answ)
                            {
                                case 1:
                                {
                                    serializationTXT();
                                    break;
                                }
                                case 2:
                                {
                                    helperL.serializationBIN();
                                    break;
                                }
                                case 3:
                                {
                                    helperL.serializationXML();
                                    break;
                                }
                                default:
                                {
                                    System.out.println("We dont save your array (");
                                    break;
                                }
                            }
                        }
                        check = false;
                        break;
                    }
                    case "9":{
                        System.out.println("Entrance to 9(sorting)");
                        helperL = sort(helperL);
                        System.out.println("list after:\n");
                        System.out.println("\n------------------------------------\n");
                        helperL.printList();
                        System.out.println("\n------------------------------------\n");
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
    public void serializationTXT(){
        File file = ConsoleFile.MenuFillOut(".txt");///pathname
        Writer out;
        try (PrintWriter pw = new PrintWriter(file.getName())) {
            System.out.println("size :" + helperL.size());
            //String sb = new String();
            for (Shops el : helperL)
            {
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
    public void deserializationTXT(){
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
                    id = line.substring(4, line.indexOf(" | name:"));
                }
                if (line.contains("name:")) {
                    name = line.substring(line.indexOf("name: ") + 6, line.indexOf(" | unit:"));
                }
                if (line.contains("unit:")) {
                    unit = line.substring(line.indexOf("unit:") + 6, line.indexOf(" | count: "));
                }
                if(line.contains("count:")){
                    count = line.substring(line.indexOf("count:") + 7, line.indexOf(" | price: "));
                }
                if(line. contains("price")){
                    price = line.substring(line.indexOf("price:") + 7, line.indexOf(" | date: "));
                }
                if(line.contains("date:")){
                    date = line.substring(line.indexOf("date:") + 6, line.indexOf(" | description: "));
                }
                if(line.contains("description:")){
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
        }
        catch(FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();	}
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
        Shops[] shops = new Shops[list.size()];
        for (int i = 0; i < shops.length; i++) {
            shops[i] = list.get(i);
        }
        System.out.print("Enter field sorted (1 - name; 2 - price; 3 - date\n>>>");
        int field = scanner.nextInt();
        bubbleSort(shops, field);
        return new HelperClassLink<>(shops);
    }

    private void bubbleSort(Shops[] array, int field) {
        boolean sorted = false;
        Shops temp;
        while(!sorted) {
            sorted = true;
            for (int i = 1; i < array.length; i++) {
                if (compare(array[i], array[i - 1], field)) {
                   swap(array, i , i-1);
                   sorted = false;
                }
            }
        }
    }

    private boolean compare(Shops a, Shops b, int field){
        switch (field){
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

