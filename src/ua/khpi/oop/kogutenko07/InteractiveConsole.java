package ua.khpi.oop.kogutenko07;

import java.util.Scanner;

/**
 * The type Interactive console.
 */
public class InteractiveConsole
{
    /**
     * Start console.
     */
    public void startConsole() {
        boolean check = true, inputFile = true;
        String input, nikname, filename;
        HelperClass helper = new HelperClass();
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Input your nikname: ");
            nikname = scanner.nextLine();
            while (check) {
                System.out.println(
                        "input   \t-\t input from file\n" +
                        "show    \t-\t show information about shops\n" +
                        "add     \t-\t add one shop\n" +
                        "remove  \t-\t remove one shop\n" +
                        "exit    \t-\t exit and save data\n"
                );
                System.out.print(nikname + "@" + nikname + ": ");
                input = scanner.nextLine();
                switch (input) {
                    case " ": {
                        break;
                    }
                    case "input": {
                        System.out.println("Enter file name: ");
                        String file = scanner.nextLine();
                        helper.deserializtion(file);
                        break;
                    }
                    case "show": {
                        helper.printSaves();
                        break;
                    }
                    case "add": {
                        helper.add();
                        break;
                    }
                    case "remove": {
                        helper.remove();
                        break;
                    }
                    case "exit": {
                        System.out.println("Enter save file name: ");
                        String file = scanner.nextLine();
                        helper.serialization(file);
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

