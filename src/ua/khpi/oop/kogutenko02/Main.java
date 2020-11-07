package ua.khpi.oop.kogutenko02;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		int min = 1;
		int max = 100;
		int diff = max - min;
		Random random = new Random();
		System.out.print(" ----------------------\n"
			          +  "|№ | num1 | num2 | gcd |\n"
			          +  " ----------------------\n"
					    );
		for (int i = 0; i < 10; i++) 
		{
			int num1 = random.nextInt(diff + 1);
			num1 += min;
			int num2 = random.nextInt(diff + 1);
			num2 += min;
			System.out.printf("|%-2d|%-6d|%-6d|%-5d|\n",i + 1, num1, num2, gcd_(num1, num2));
			System.out.print(" ----------------------\n");
		}
	}

	static int gcd_(int a, int b)
	{
	  while(a != 0 && b != 0)
	  {
	     int c = b;
	     b = a % b;
	     a = c;
	  }
	  return a + b; // 
	}
}
