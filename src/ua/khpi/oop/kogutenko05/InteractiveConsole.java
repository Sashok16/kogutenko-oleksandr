package ua.khpi.oop.kogutenko05;

import java.util.Scanner;

/**
 * The type Interactive console.
 */
public class InteractiveConsole
{
    public void startConsole() {
        boolean check = true, checkHelpLine = true;
        String input, nikname;
        HelperClass helper = new HelperClass();
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Input your nikname: ");
            nikname = scanner.nextLine();
            while (check) {
                if (checkHelpLine) {
                    System.out.println("Hello, my name is Alex Kogutenko\n"
                            + "I am from Ukrain and studing at NTU \"KHPI\"\n"
                            + "This is a test console-project with a debug programs.\n"
                            + "Such commands are present so far:\n"
                            + "\t-h | -help   \t-\t command for summary information about other commands (important to remmember!)\n"
                            + "\t-d | -debug  \t-\t file debugger command\n"
                            + "\tchtext       \t-\t changed the text as in the past lab work (lab work 3)\n"
                            + "\tshow         \t-\t show reserved array.\n"
                            + "\tedit         \t-\t edit reserved array.\n"
                            + "\texit         \t-\t exit form program\n");
                    checkHelpLine = false;
                }
                System.out.print(nikname + "@" + nikname + ": ");
                input = scanner.nextLine();
                switch (input) {
                    case " ": {
                        break;
                    }
                    case "-h": {
                        helper.printHelpInfo();
                        break;
                    }
                    case "-help": {
                        helper.printHelpInfo();
                        break;
                    }
                    case "-d": {
                        helper.printDebuggerInHelper();
                        break;
                    }
                    case "-debug": {
                        helper.printDebuggerInHelper();
                        break;
                    }
                    case "chtext": {
                        helper.changedText();
                        break;
                    }
                    case "show": {
                        helper.printSaves();
                        break;
                    }
                    case "edit": {
                        helper.edit();
                        break;
                    }
                    case "exit": {
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

