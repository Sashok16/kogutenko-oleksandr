package ua.khpi.oop.kogutenko04;

import java.lang.StringBuilder;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

/**
 * The type Helper class with console.
 */
public class HelperClassWithConsole {

	private StringBuilder str;
	private Integer count;
	private Integer length;

	/**
	 * Instantiates a new Helper class with console.
	 */
	public HelperClassWithConsole() {
		str = new StringBuilder("EmptyLine");
		count = 0;
		length = 0;

	}

	/**
	 * Instantiates a new Helper class with console.
	 *
	 * @param str the str
	 */
	public HelperClassWithConsole(String str) {
		if (str.isEmpty()) {
			System.out.println("Line is empty");
			this.str = new StringBuilder("EmptyLine");
		} else {
			// System.out.println("Constract with param");
			this.str = new StringBuilder(str);
			count = CountWordsInHelper();
			length = this.str.length();
		}
	}

	/**
	 * Gets info of helper object.
	 *
	 * @return the info of helper object
	 */
	public String getInfoOfHelperObject()
	{
		return str.getClass().getSimpleName() + " 'str' has '" + getStr() + "'\n"
				+ count.getClass().getSimpleName() + " 'count' has '" + getCount() + "'\n"
				+ length.getClass().getSimpleName() + " 'length' has '" + getLength() + "'";
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
	public void setStr(String str) {
		this.str = new StringBuilder(str);
	}

	/**
	 * Gets str.
	 *
	 * @return the str
	 */
	public StringBuilder getStr()
	{
		return str;
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
		StringBuilder line = new StringBuilder(str);
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
		System.out.println("Hello, my name is Alex Kogutenko\n" + "I am from Ukrain and studing at NTU \"KHPI\"\n"
				+ "This is a test console-project with a debug programs.\n" + "Such commands are present so far:\n"
				+ "\t-h | -help  \t-\t command for summary information about other commands (important to remmember!)\n"
				+ "\t-d | -debug \t-\t file debugger command\n" + "\t-clear      \t-\t cleaning the console\n");
		System.out.println("Tap any key...");
	}

	/**
	 * Debugger in helper.
	 */
	public void debuggerInHelper()
	{
		Date date = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("'\nDate:' dd.MM.yyyy '\nTime: 'HH:mm:ss '\n'" );
		System.out.println(formatDate.format(date) + "values of the variables:\n"
							+ getInfoOfHelperObject());

	}

}
