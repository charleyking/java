package club.charleyking.interview;

public class StringChange {
	public static String string = "hei";
	public static void main(String[] args) {
		//String string = "1234";
		int a = 2;
		change(a);
		change(string);
		System.out.println(string + a);
	}
	
	public static void change(String string) {
		 string = "hello";
	}
	public static void change(int a) {
		a = 3;
	}
	public static void changeObj(Integer in) {
		in = 12;
	}
}
