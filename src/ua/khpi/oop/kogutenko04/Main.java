package ua.khpi.oop.kogutenko04;

import ua.khpi.oop.kogutenko03.HelperClassWithString;

import java.util.Scanner;

/**
 * The type Main.
 */
//Look I was gonna go easy on you and not to hurt your feelings but I am only going to get this one chance
/*
 * Використовуючи програму рішення завдання лабораторної роботи №3, 
 * відповідно до прикладної задачі забезпечити обробку команд користувача у вигляді текстового меню:
 *
 * -введення даних;
 * -перегляд даних;
 * -виконання обчислень;
 * -відображення результату;
 * -завершення програми і т.д.
 * 
 * Забезпечити обробку параметрів командного рядка для визначення режиму роботи програми:
 * -параметр "-h" чи "-help": відображається інформація про автора програми, 
 *  призначення (індивідуальне завдання), детальний опис режимів роботи 
 *  (пунктів меню та параметрів командного рядка);
 * -параметр "-d" чи "-debug": в процесі роботи програми відображаються додаткові дані, 
 *  що полегшують налагодження та перевірку працездатності програми: 
 *  діагностичні повідомлення, проміжні значення змінних, значення тимчасових змінних та ін.
 */
public class Main {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args)
	{
		start_();
	}

	private static void start_()
	{
		boolean check = true, checkHelpLine = true;
		String input, nikname;
		HelperClassWithConsole helper = new HelperClassWithConsole();

		try (Scanner scanner = new Scanner(System.in))
		{
			System.out.print("Input your nikname: ");
			nikname = scanner.nextLine();
			//clearConsole();
			while (check)
			{
				if (checkHelpLine) {
					System.out.println("Hello, my name is Alex Kogutenko\n"
							+ "I am from Ukrain and studing at NTU \"KHPI\"\n"
							+ "This is a test console-project with a debug programs.\n"
							+ "Such commands are present so far:\n"
							+ "\t-h | -help  \t-\t command for summary information about other commands (important to remmember!)\n"
							+ "\t-d | -debug \t-\t file debugger command\n"
							+ "\tchtext\t-\t changed the text as in the past lab work (lab work 3)\n");
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
					helper.debuggerInHelper();
					break;
				}
				case "-debug": {
					System.out.println("debug");
					break;
				}
				case "chtext": {
					System.out.println();
					System.out.println("Enter the text. In the text,\nreplace the words of the specified length with the specified line");
					try (Scanner scan = new Scanner(System.in))
					{
						System.out.print("Main line: ");
						String mainStr = scan.nextLine();
						HelperClassWithString mainHelperStr = new HelperClassWithString(mainStr);

						System.out.print("Enter number of letters in word which you want to changed: ");
						int length = scan.nextInt();

						System.out.print("Enter word to replace: ");
						scan.nextLine();
						String newWord = scan.nextLine();


						String newStr = mainHelperStr.replaceAllWordsOn(length, newWord);//new String(mainHelperStr.replaceAllWordsOn(length, newWord));
						System.out.println("----------------------------------------------");
						System.out.println("No changed line: " + mainStr);
						System.out.println("Result:          " + newStr);
					}
					catch (Exception e)
					{
						System.out.println(e);
					}
					break;
				}
				default: {
					System.out.println("(" + input + ") I don't know this command :(");
					break;
				}
				}
			}
			scanner.close();
		} catch (Exception e) {
			System.out.println(e);
			check = false;
		}
	}
}
