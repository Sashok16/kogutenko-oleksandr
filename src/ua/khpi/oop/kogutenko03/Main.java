package ua.khpi.oop.kogutenko03;

import java.util.Scanner;

/**
 * The type Main.
 */
//Look I was gonna go easy on you and not to hurt your feelings but I am only going to get this one chance
//import java.lang.StringBuilder;
/*
 * Ввести текст. 
 * У тексті слова заданої довжини замінити зазначеним рядком. 
 * Вивести початковий текст та результат.
 */
public class Main {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args)
	{
		try (Scanner scanner = new Scanner(System.in)) 
        {
			System.out.print("Main line: ");
			String mainStr = scanner.nextLine();
			HelperClassWithString mainHelperStr = new HelperClassWithString(mainStr);
						
			System.out.print("Enter number of letters in word which you want to changed: ");
			int length = scanner.nextInt();
			
			System.out.print("Enter word to replace: ");
			scanner.nextLine();
			String newWord = scanner.nextLine();
			
			
			String newStr = mainHelperStr.replaceAllWordsOn(length, newWord);//new String(mainHelperStr.replaceAllWordsOn(length, newWord));
			System.out.println("----------------------------------------------");
			System.out.println("No changed line: " + mainStr);
			System.out.println("Result:          " + newStr);
			scanner.close();
		} 
        catch (Exception e)
        {
        	System.out.println(e);
        }
		
	}

}
