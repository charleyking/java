package club.charleyking.interview;

public class StringChange {
	public static void main(String[] args) {
		Integer in = new Integer(2);
		int n = 3;
		change(n);
		changeObj(in);
		System.out.println(n);
		System.out.println(in);
	}
	
	public static void change(int n) {
		 n = 10;
	}
	public static void changeObj(Integer in) {
		in = 12;
	}
}
