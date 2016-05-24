import java.util.*;

public class FibonacciTest {
	public static void main(String[] args) {
		System.out.println("Please input an int index");
		Scanner scn = new Scanner(System.in);
		int index = scn.nextInt();
		System.out.println("the fibonacci number is " + fibonacci(index));
	}

	public static int fibonacci(int n) {
		if (n==0) {
			return 0;
		} else if (n==1) {
			return 1;
		} else {
			return fibonacci(n-1) + fibonacci(n-2);
		}
	}
}