package club.charleyking.string;

import javax.swing.JOptionPane;

public class StringReverse {
	public String reverse(String str) {
		StringBuilder stringBuilder = new StringBuilder(str);
		return stringBuilder.reverse().toString();
	}
	
	public static void main(String[] args) {
		StringReverse stringReverse = new StringReverse();
		String a = stringReverse.reverse("abcdefg");
		System.out.println(a);
	}
}
