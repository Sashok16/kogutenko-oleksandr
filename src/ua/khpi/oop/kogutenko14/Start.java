package ua.khpi.oop.kogutenko14;

/**
 * The type Start.
 */
public class Start {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) throws InterruptedException {
        try{
            if (args[0].equals("-auto")) {
                ConsoleAuto ca = new ConsoleAuto();
                ca.startConsole();
            } else {
                InteractiveConsole console = new InteractiveConsole();
                console.startConsole();
            }
        } catch(InterruptedException e) {e.printStackTrace();}
    }
}