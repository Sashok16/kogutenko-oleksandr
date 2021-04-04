package ua.khpi.oop.kogutenko12;

/**
 * The type Start.
 */
public class Start {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        if (args[0].equals("-auto")) {
            ConsoleAuto ca = new ConsoleAuto();
            ca.startConsole();
        } else {
            InteractiveConsole console = new InteractiveConsole();
            console.startConsole();
        }
    }
}