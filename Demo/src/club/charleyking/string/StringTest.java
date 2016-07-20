package club.charleyking.string;

public class StringTest {
	public static String reverse(String origin) {
		if (origin == null || origin.length()<1) {
			return origin;
		} else {
			return reverse(origin.substring(1)) + origin.charAt(0);
		}
	}
}
