package club.charleyking.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsTest {
	public static void main(String[] args) {
		reverseList();
		shuffleList();
		sortList();
	}
	
	/* use Collections.reverse() method can easily reverse the order in the list*/
	public static void reverseList() {
		List<Integer> alist = new ArrayList<Integer>();
		for (int i=0; i<=9; i++) {
			alist.add(i);
		}
		Collections.reverse(alist);
		System.out.println(alist);
	}
	
	/* use Collections.shuffle() method to shuffle the order in the list */
	public static void shuffleList() {
		List<Integer> alist = new ArrayList<Integer>();
		for (int i=0; i<=9; i++) {
			alist.add(i);
		}
		Collections.shuffle(alist);
		System.out.println(alist);
	}
	
	/* use Collections.sort() method to sort the order in the list */
	public static void sortList() {
		List<Integer> alist = new ArrayList<Integer>();
		for (int i=0; i<=9; i++) {
			alist.add(i);
		}
		Collections.sort(alist);
		System.out.println(alist);
	}
}
