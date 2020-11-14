package ua.khpi.oop.kogutenko03;
import java.lang.StringBuilder;

/**
 * The type Helper class with string.
 */
public class HelperClassWithString
{
	private static StringBuilder str;
	private static int count;
	private static int length;

	/**
	 * Instantiates a new Helper class with string.
	 *
	 * @param str the str
	 */
	public HelperClassWithString(String str)
	{
		if(str.isEmpty())
		{
			System.out.println("Line is empty");
			this.str = new StringBuilder("EmptyLine");
		}
		else
		{
			//System.out.println("Constract with param");
			this.str = new StringBuilder(str);
			CountWordsInHelper();
			length = this.str.length();
		}
	}

	/**
	 * Sets length.
	 *
	 * @param len the length
	 */
	public static void setLength(int len)
	{
		length = len;
	}

	/**
	 * Gets length.
	 *
	 * @return the length
	 */
	public static int getLength()
	{
		return length;
	}

	/**
	 * Sets str.
	 *
	 * @param s the str
	 */
	public static void setStr(String s)
	{
		str = new StringBuilder(s);
	}

	/**
	 * Gets str.
	 *
	 * @return the str
	 */
	public static StringBuilder getStr()
	{
		return str;
	}

	/**
	 * Gets count.
	 *
	 * @return the count
	 */
	public static int getCount()
	{
		return count;
	}

	/**
	 * Sets count.
	 *
	 * @param c the count
	 */
	public static void setCount(int c)
	{
		count = c;
	}

	/**
	 * Count words in helper int.
	 *
	 * @return the int
	 */
	public static int CountWordsInHelper()
	{
		int count = 0;
		if(str.length() != 0){
            count++;
            //Проверяем каждый символ, не пробел ли это
            for (int i = 0; i < str.length(); i++) 
            {
                if(str.charAt(i) == ' ')
                {
                    //Если пробел - увеличиваем количество слов на 1
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
	public static int CountWordsInString(String str)
	{
		int count = 0;
		if(str.length() != 0){
            count++;
            //Проверяем каждый символ, не пробел ли это
            for (int i = 0; i < str.length(); i++) 
            {
                if(str.charAt(i) == ' ')
                {
                    //Если пробел - увеличиваем количество слов на 1
                    count++;
                }
            }
        } 
		return count;
	}
	
	private static int[] indexingSpaces(StringBuilder line)
	{
		int[] index = new int[getCount() + 1];
		if(line.length() != 0)
		{
            //System.out.println("Indexing...");
            for (int i = 0, indx = 1; i < line.length(); i++) 
            {
            	//System.out.printf("%-3c_", line.charAt(i));
                if(   line.charAt(i) == ' ' 
                   || line.charAt(i) == '.' 
                   || line.charAt(i) == '!' 
                   || line.charAt(i) == '?'
                   || line.charAt(i) == ','
                   || line.charAt(i) == ','
                   || line.charAt(i) == '\0'
						|| line.charAt(i) == ',' && line.charAt(i + 1) == ' '
						|| line.charAt(i) == '!' && line.charAt(i + 1) == ' '
						|| line.charAt(i) == '?' && line.charAt(i + 1) == ' '
						|| line.charAt(i) == '\n'
						|| line.charAt(i) == '\0' && line.charAt(i) == '\n'
				)
                {
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
	public static String replaceAllWordsOn(int len, String onLine)
	{
		StringBuilder line = new StringBuilder(str);
		int[] indexSpace = indexingSpaces(line);
		indexSpace[0] = -1;
		for (int i = 0; i < indexSpace.length - 1; i++) 
		{
			if(i == 0 && ( indexSpace[i + 1] - indexSpace[i] - 1 == len ) )
			{
				//System.out.println("\n\nfirst indexing: " + res);
				line.delete(indexSpace[i] + 1, indexSpace[i + 1]);
				line.insert(indexSpace[i] + 1, onLine);				
				indexSpace = indexingSpaces(line);
			}
			else if( i > 0 && Math.abs(indexSpace[i + 1] - Math.abs(indexSpace[i]) - 1) == len) // ? | |[i + 1]| - [i] | == len ?
			{
				//line.replace(indexSpace[i] + 1, indexSpace[i + 1], onLine);
				line.delete(indexSpace[i] + 1, indexSpace[i + 1]);
				line.insert(indexSpace[i] + 1, onLine);
				indexSpace = indexingSpaces(line);
			}
		}
		String output =new String(line);
		return output;
	}
}
