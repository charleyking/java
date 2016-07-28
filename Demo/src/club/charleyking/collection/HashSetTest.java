package club.charleyking.collection;

import java.util.HashSet;

public class HashSetTest {
	/*hashSet has no guarantee to the order of object that you add to it*/
	public static void main(String[] args) {
		HashSet<Integer> hs = new HashSet<Integer>();
		hs.add(8);
		hs.add(3);
		hs.add(9);
		System.out.println(hs);
	}
}
