package ua.khpi.oop.kogutenko04;

import java.util.Scanner;

/**
 * The type Main.
 */
public class Main {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args)
	{
		HelperClass helper = new HelperClass();
		boolean help = informHelp(args);
		boolean debug = informDebug(args);
		boolean nextAction = true;
		int input;
		Scanner sc = new Scanner(System.in);
		while(nextAction) {
			System.out.println("Input choice\n" +
					"\t1 - next sentence\n" +
					"\t2 - exit\n" +
					"Enter choice > ");
			input = sc.nextInt();
			switch(input)
			{
				case 1:
					if (help == false && debug == false) {
						helper.changedText();
					} else if (help == true && debug == false) {
						helper.printHelpInfo();
						helper.changedText();
					} else if (help == false && debug == true) {
						helper.changedText();
						helper.debuggerInHelper();
					} else if (help == true && debug == true) {
						helper.printHelpInfo();
						helper.changedText();
						helper.debuggerInHelper();
					}
					break;
				case 2:
					nextAction = false;
					break;

			}

		}
	}

	private static boolean informHelp(String args[])
	{
		int index;
		boolean help = false;
		for (index = 0; index < args.length; index++) {
			String opt = args[index];
			switch (opt) {
				case "-h":
					help = true;
					System.out.println("Helper turn on");
					break;
				case "-help":
					help = true;
					System.out.println("Helper turn on");
					break;
				default:
					if (!opt.isEmpty() && opt.charAt(0) == '-') {
						System.out.println("is empty param");
					}
			}
		}
		return help;
	}

	private static boolean informDebug(String args[])
	{
		int index;
		boolean debug = false;
		loop:
		for (index = 0; index < args.length; index++) {
			String opt = args[index];
			switch (opt) {
				case "-d":
					debug = true;
					System.out.println("Debuger turn on");
					break;
				case "-debug":
					debug = true;
					System.out.println("Debuger turn on");
					break;
				default:
					if (!opt.isEmpty() && opt.charAt(0) == '-') {
						System.out.println("is empty param");
					}
					break loop;
			}
		}
		return debug;
	}
}