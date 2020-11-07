package ua.khpi.oop.kogutenko01;

//import java.util.Arrays;

public class Main {
	public static int jNumber = 0x0B; // 11
	public static long phNumber = 380507080028L; //long integer for phone number
	public static int phNumber2 = 0b11100;
	public static int phNumber8 = 017224; // 7_8__28
	public static int chengedJNumber = ((jNumber - 1) % 26) + 1;
	public static char literal = (char)(65 + chengedJNumber); // L
	
	public static void main(String[] args)
	{
		
		count_(jNumber, "jNumber");
		count_(phNumber, "phNumber");
		count_(phNumber2, "phNumber2");
		count_(phNumber8, "phNumber8");
		count_(chengedJNumber, "chengedJNumber");
		count_(literal, "literal");
		
		binaryCountOne(jNumber, "jNumber");
		binaryCountOne(phNumber, "phNumber");
		binaryCountOne(phNumber2, "phNumber2");
		binaryCountOne(phNumber8, "phNumber8");
		binaryCountOne(chengedJNumber, "chengedJNumber");
		binaryCountOne(literal, "literal");
		
		System.out.println(Integer.toString(jNumber,2));
		
		
	}
	
	public static void  count_(long num, String name)
	{
		 int paired = 0, unpaired = 0;
		 long check = 0;
		 long saveNum = num;
		 while (num != 0)
		 {
			 check = num % 10;
			 if((check % 2) == 0)
			 {
				 paired++;
			 }
			 else 
			 {
				unpaired++;
			 }
			 num /= 10;
		 }
		 System.out.println("paired numbers in \"" + name + "\" is " + paired + " (" + saveNum + ")");
		 System.out.println("unaired numbers in \"" + name + "\" is " + unpaired + " (" + saveNum + ")");
	}
	public static void binaryCountOne(long num, String name)
	{
		short count = 0;
		long binary_ = 0, saveNum = num;
		while (num != 0)
		{
			binary_ = num % 2;
			if (binary_ == 1)
			{
				count++;
			}
			num /= 2;
		}
		System.out.println("1 binary numbers in \"" + name + "\" is " + count + " (" + saveNum + ")");
	//	System.out.println(Integer.toString(num,2));
	}
}
