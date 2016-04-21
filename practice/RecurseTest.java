import java.util.*;

public class RecurseTest {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Please input a int");
		int n = scn.nextInt();
		System.out.println("the factorial of n is " + factorial(n));
	}

	public static int factorial(int n) {
		if (n==0) {
			return 1;
		} else {
			return n*factorial(n-1);
		}
	}
}