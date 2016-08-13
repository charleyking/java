package club.charleyking.string;

public class StringBuilderTest {
	public static void main(String[] args) {
		String a = "hello, i'm string.";
		System.out.println(a);
		for (int n=0; n<10; n++) {
			StringBuffer b = new StringBuffer("this is " + n);
			System.out.println(b);
		}
	}
}
