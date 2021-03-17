package ua.khpi.oop.kogutenko09;

import java.util.Scanner;

/**
 * The type Interactive console.
 */
public class InteractiveConsole
{
    private String  nikname;

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
        int answerDeserialization = 0;
        boolean check = true, checkChoicelist = false, list = true;
        String input;
        HelperClass helper = new HelperClass();
        HelperClassLink<Shops> helperL = new HelperClassLink<>();
        Scanner scanner = new Scanner(System.in);
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

                System.out.println(
                        "1 / input   \t-\t input from file\n" +
                        "2 / show    \t-\t show information about shops\n" +
                        "3 / add     \t-\t add one shop\n" +
                        "4 / remove  \t-\t remove one shop\n" +
                        "5 / switch  \t-\t switch to another list\n" +
                        "0 / exit    \t-\t exit and save data\n"
                );
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
                            System.out.println("what deserialization do you want?\n(1 - bin, 2 - xml, 3 - txt)>>>");
                            answerDeserialization = scanner.nextInt();
                            switch (answerDeserialization)
                            {
                                case 1 :
                                {
                                    helperL.deserializtionBIN();
                                    break;
                                }
                                case 2 :
                                {
                                    helperL.deserializtionXML();
                                    break;
                                }
                                case 3 :
                                {
                                    helperL.deserializtionTXT();
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
                            System.out.println("what deserialization do you want?\n(1 - bin, 2 - xml, 3 - txt)>>>");
                            answerDeserialization = scanner.nextInt();
                            switch (answerDeserialization)
                            {
                                case 1 :
                                {
                                    helperL.deserializtionBIN();
                                    break;
                                }
                                case 2 :
                                {
                                    helperL.deserializtionXML();
                                    break;
                                }
                                case 3 :
                                {
                                    helperL.deserializtionTXT();
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
                            System.out.println("Enter number of id: ");
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
                            System.out.println("Enter number of id: ");
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
                            System.out.println(
                                    "array list  - 1\n" +
                                    "linked list - 2\n" +
                                    "what list do you want to use? (1 or 2)\n");
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
                    case "5": {
                        if (checkChoicelist == false) checkChoicelist = !checkChoicelist;
                        boolean checkListAnsw = true;
                        while (checkListAnsw)
                        {
                            System.out.println(
                                    "array list  - 1\n" +
                                    "linked list - 2\n" +
                                    "what list do you want to use? (1 or 2)\n");
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
                                    helperL.serializationTXT();
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
                            switch (answerDeserialization)
                            {
                                case 0:
                                {
                                    System.out.println("What save do you want? (1 - .txt; 2 - .bin; 3 - .xml) ");
                                    int answ = scanner.nextInt();
                                    switch (answ)
                                    {
                                        case 1:
                                        {
                                            helperL.serializationTXT();
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
                                    break;
                                }
                                case 1 :
                                {
                                    helperL.deserializtionBIN();
                                    break;
                                }
                                case 2 :
                                {
                                    helperL.deserializtionXML();
                                    break;
                                }
                                case 3 :
                                {
                                    helperL.deserializtionTXT();
                                    break;
                                }
                            }
                        }
                        check = false;
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
}