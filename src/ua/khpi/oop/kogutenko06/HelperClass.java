package ua.khpi.oop.kogutenko06;


import Laba3.Helper;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * The type Helper class with console.
 */
public class HelperClass extends Object{

	private Array<String> save = new SaveArray<>();
	private StringBuilder strBefore;
	private String strAfter;
	private Integer count;
	private Integer length;


	/**
	 * Instantiates a new Helper class with console.
	 */
	public HelperClass() {
		strBefore = new StringBuilder("EmptyLine");
		strAfter = new String("EmptyLine");
		count = 0;
		length = 0;

	}

	/**
	 * Instantiates a new Helper class with console.
	 *
	 * @param str the str
	 */
	public HelperClass(String str) {
		if (str.isEmpty()) {
			System.out.println("Line is empty");
			this.strBefore = new StringBuilder("EmptyLine");
			this.strAfter = new String("EmptyLine");
		} else {
			// System.out.println("Constract with param");
			this.strBefore = new StringBuilder(str);
			this.strAfter = new String("EmptyLine");
			save.add(str);
			count = CountWordsInHelper();
			length = this.strBefore.length();
		}
	}

	/**
	 * Gets info of helper object.
	 *
	 * @return the info of helper object
	 */
	@Override
	public String toString()
	{
		return strBefore.getClass().getSimpleName() + " 'strBefore' has '" + getStrBefore() + "'\n"
				+ strAfter.getClass().getSimpleName() + " 'strAfter' has '" + getStrAfter() + "'\n"
				+ count.getClass().getSimpleName() + " 'count' has '" + getCount() + "'\n"
				+ length.getClass().getSimpleName() + " 'length' has '" + getLength() + "'";
	}

	/**
	 * Sets info helper object.
	 *
	 * @param str the str
	 */
	public void setInfoHelperObject(String str)
	{
		setStrBefore(str);
		setCount(CountWordsInHelper());
		setLength(str.length());
	}

	/**
	 * Gets str after.
	 *
	 * @return the str after
	 */
	public String getStrAfter() {
		return strAfter;
	}

	/**
	 * Sets str after.
	 *
	 * @param strAfter the str after
	 */
	public void setStrAfter(String strAfter) {
		this.strAfter = strAfter;
	}


	/**
	 * Sets length.
	 *
	 * @param length the length
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * Gets length.
	 *
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Sets str.
	 *
	 * @param str the str
	 */
	public void setStrBefore(String str) {
		this.strBefore = new StringBuilder(str);
	}

	/**
	 * Gets str.
	 *
	 * @return the str
	 */
	public StringBuilder getStrBefore()
	{
		return strBefore;
	}

	/**
	 * Gets count.
	 *
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Sets count.
	 *
	 * @param count the count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Count words in helper int.
	 *
	 * @return the int
	 */
	public int CountWordsInHelper() {
		int count = 0;
		if (strBefore.length() != 0) {
			count++;
			// Проверяем каждый символ, не пробел ли это
			for (int i = 0; i < strBefore.length(); i++) {
				if (strBefore.charAt(i) == ' ') {
					// Если пробел - увеличиваем количество слов на 1
					count++;
				}
			}
		}
		setCount(count);
		return getCount();
	}

	/**
	 * Count words in string int.
	 *
	 * @param str the str
	 * @return the int
	 */
	public int CountWordsInString(String str) {
		int count = 0;
		if (str.length() != 0) {
			count++;
			// Проверяем каждый символ, не пробел ли это
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == ' ') {
					// Если пробел - увеличиваем количество слов на 1
					count++;
				}
			}
		}
		return count;
	}

	private int[] indexingSpaces(StringBuilder line) {
		int[] index = new int[getCount() + 1];
		if (line.length() != 0) {
			// System.out.println("Indexing...");
			for (int i = 0, indx = 1; i < line.length(); i++) {
				// System.out.printf("%-3c_", line.charAt(i));
				if (line.charAt(i) == ' ' || line.charAt(i) == '.' || line.charAt(i) == '!' || line.charAt(i) == '?'
						|| line.charAt(i) == ',' || line.charAt(i) == ',' || line.charAt(i) == '\0') {
					index[indx++] = i;
				}
			}
		}
		return index;
	}

	/**
	 * Replace all words on string.
	 *
	 * @param len    the len
	 * @param onLine the on line
	 * @return the string
	 */
	public String replaceAllWordsOn(int len, String onLine) {
		StringBuilder line = new StringBuilder(strBefore);
		int[] indexSpace = indexingSpaces(line);
		indexSpace[0] = -1;
		for (int i = 0; i < indexSpace.length - 1; i++) {
			if (i == 0 && (indexSpace[i + 1] - indexSpace[i] - 1 == len)) {
				// System.out.println("\n\nfirst indexing: " + res);
				line.delete(indexSpace[i] + 1, indexSpace[i + 1]);
				line.insert(indexSpace[i] + 1, onLine);
				indexSpace = indexingSpaces(line);
			} else if (i > 0 && Math.abs(indexSpace[i + 1] - Math.abs(indexSpace[i]) - 1) == len) // ? | |[i + 1]| - [i]
																									// | == len ?
			{
				// line.replace(indexSpace[i] + 1, indexSpace[i + 1], onLine);
				line.delete(indexSpace[i] + 1, indexSpace[i + 1]);
				line.insert(indexSpace[i] + 1, onLine);
				indexSpace = indexingSpaces(line);
			}
		}

		String output = new String(line);
		return output;
	}

	/**
	 * Print help info.
	 */
	public void printHelpInfo() {
		System.out.println("Such commands are present so far:\n"
				+ "\t-h | -help  \t-\t command for summary information about other commands (important to remember!)\n"
				+ "\t-d | -debug \t-\t file debugger command\n"
				+ "\tchtext\t-\t changed the text as in the past lab work (lab work 3)\n"
				+ "\tshow  \t-\t show and edit array of changed texts"
				+ "\texit  \t-\t exit form program\n");
		//System.out.println("Tap any key...");
	}

	/**
	 * Debugger in helper.
	 */
	public void printDebuggerInHelper()
	{
		Date date = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("'\nDate:' dd.MM.yyyy '\nTime: 'HH:mm:ss '\n'" );
		System.out.println(formatDate.format(date) + "values of the variables:\n" + this.toString());
	}

	/**
	 * Print saves.
	 */
	public void printSaves()
	{
		System.out.println(save.toString());
	}

	/**
	 * Edit.
	 */
	public void edit()
	{
		boolean check = true;
		String input;
		//HelperClass helper = new HelperClass();
		Scanner scanner = new Scanner(System.in);
		try {
			while (check) {
				System.out.print(
						  "Such commands are present so far:\n"
						+ "\tremove       \t-\t remove element\n"
						+ "\tcontain      \t-\t boolean contain of some var\n"
						+ "\tclear        \t-\t clear all array\n"
				        + "\texit         \t-\t exit from edit\n-> ");
				input = scanner.nextLine();
				switch (input) {
					case "remove": {
						if(save.size() > 0) {
							System.out.print("Enter some INDEX of string which can contains at this array and you want to delete\n-> ");
							int in = scanner.nextInt();
							if (save.remove(in)) {
								System.out.println("removed successful");
							}
							else {
								System.out.println("we don't search this string");
							}
						}
						else {
							System.out.println("Array is empty");
						}
						break;
					}
					case "contain": {
						if(save.size() > 0) {
							System.out.print("Enter some string which can contains at this array\n-> ");
							String in = scanner.nextLine();
							if (save.contains(in)) {
								System.out.println("\"" + in + "\" is contains");
							} else {
								System.out.println("we don't search this string");
							}
						}else {
							System.out.println("Array is empty");
						}
						break;
					}
					case "clear": {
						if(save.size() > 0)
						{
							save.clear();
						}
						else {
							System.out.println("Array is empty");
						}
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
			//System.out.println("GOOD BEY!!!");
		} catch (Exception e) {
			System.out.println(e);
			check = false;
		}
	}

	/**
	 * Changed text.
	 *
	 * @throws Exception the exception
	 */
	public void changedText() throws Exception {
		Scanner scan = new Scanner(System.in);
		if(save.size() == 0) {
			System.out.println();
			System.out.println("Enter the text. In the text,\nreplace the words of the specified length with the specified line");
			System.out.print("Main line: ");
			String mainStr = scan.nextLine();
			save.add(mainStr);
			setInfoHelperObject(mainStr);
			System.out.print("Enter number of letters in word which you want to changed: ");
			int length = scan.nextInt();
			System.out.print("Enter word to replace: ");
			scan.nextLine();
			String newWord = scan.nextLine();
			String newStr = replaceAllWordsOn(length, newWord);
			setStrAfter(newStr);
			System.out.println("----------------------------------------------");
			System.out.println("No changed line: " + mainStr);
			System.out.println("Result:          " + newStr);
		}
		else if(save.size() > 0)
		{
			System.out.print("Do you want to change some string from array or your string?(my - 0, array - 1) -> ");
			int answer = scan.nextInt();
			scan.nextLine();
			if(answer == 0) {
				System.out.println();
				System.out.println("Enter the text. In the text,\nreplace the words of the specified length with the specified line");
				System.out.print("Main line: ");
				String mainStr = scan.nextLine();
				save.add(mainStr);
				setInfoHelperObject(mainStr);
				System.out.print("Enter number of letters in word which you want to changed: ");
				int length = scan.nextInt();
				System.out.print("Enter word to replace: ");
				scan.nextLine();
				String newWord = scan.nextLine();
				String newStr = replaceAllWordsOn(length, newWord);
				setStrAfter(newStr);
				System.out.println("----------------------------------------------");
				System.out.println("No changed line: " + mainStr);
				System.out.println("Result:          " + newStr);
			}
			else if(answer == 1) {
				printSaves();
				System.out.println("Enter a number of string: ");
				int choice = scan.nextInt();
				scan.nextLine();
				String mainStr = save.get(choice);
				System.out.print("Main line: " + mainStr);
				setInfoHelperObject(mainStr);
				System.out.print("Enter number of letters in word which you want to changed: ");
				int length = scan.nextInt();
				System.out.print("Enter word to replace: ");
				scan.nextLine();
				String newWord = scan.nextLine();
				String newStr = replaceAllWordsOn(length, newWord);
				setStrAfter(newStr);
				System.out.println("----------------------------------------------");
				System.out.println("No changed line: " + mainStr);
				System.out.println("Result:          " + newStr);
			}
			else {
				System.out.println("We don't search this elem :(");
			}
		}
	}

	public void serialization(String savefile)
	{
		File file = new File(savefile);///pathname
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(save.size());
			System.out.println("size :" + save.size());
			for (String el : save)
			{
				oos.writeObject(el);
				System.out.println(el);
			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deserializtion(String savefile) {
		//Array<Shop> container = new SaveArray<>();
		File file = new File(savefile);///pathname
		if(file.length() != 0) {
			try {
				FileInputStream fis = new FileInputStream(file);///pathname
				ObjectInputStream ois = new ObjectInputStream(fis);
				Integer count = ois.readInt();
				for (int i = 0; i < count; i++)
				{
					save.add((String) ois.readObject());
					System.out.println((String)ois.readObject());
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("File is empty");
		}
	}

	public void anotherClass()
	{
		Helper helper = new Helper();
		StringBuilder s;
		s = helper.AddTextSB();//
		helper.PrintLineSB(s);//
		s =helper.Task6(s);//
		helper.PrintNewLine(s);//
	}
}
