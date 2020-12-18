package ua.khpi.oop.kogutenko04;

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
		String[] args_ = new String[2];
		args = args_;
		args[0] = "-h";
		args[1] = "-d";
		System.out.println(args[0]);
		HelperClass helper = new HelperClass();
		boolean help = false;
		boolean debug = false;
		int index;
		loop:
		for (index = 0; index < args.length; index++) {
			String opt = args[index];
			switch (opt) {
				case "-h":
					help = true;
					System.out.println("Helper");
					break;
				case "-d":
					debug = true;
					System.out.println("Debuger");
					break;
				case "-help":
					help = true;
					break;
				case "-debug":
					debug = true;
					break;
				default:
					if (!opt.isEmpty() && opt.charAt(0) == '-') {
						error("Unknown option: " + opt);
					}
					break loop;
			}
		}

		if(help == false && debug == false)
		{
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
	}

	private static void error(String message) {
		if (message != null) {
			System.err.println(message);
		}
		System.err.println("usage: myapp [-h] [-d]");
		System.exit(1);
	}
}