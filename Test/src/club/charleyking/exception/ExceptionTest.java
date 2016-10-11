package club.charleyking.exception;

public class ExceptionTest {
	public static void main(String[] args) {
		int a;
		int b;
		int c;
		try {
			a = 3;
			b = 0;
			c = a / b;
		} catch (ArithmeticException e) {
			System.out.println("he");
		}
	}
}
