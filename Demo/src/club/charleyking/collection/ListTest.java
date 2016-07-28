package club.charleyking.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListTest {
	public static void main(String[] args) {
		List<Student> alist = new ArrayList<Student>();
		for (int i=0; i<=100000; i++) {
			Student stu = new Student("tom" + i, i);
			alist.add(stu);
		}
		Collections.shuffle(alist);
		for (int i=0; i<=4; i++) {
			long start = System.currentTimeMillis();
			for (Student stu: alist) {
				if (stu.getName().equals("tom8888")) {
					System.out.println("find target");
					break;
				}
			}
			long end = System.currentTimeMillis();
			long elapse = end-start;
			System.out.println("the " + i + " time use " + elapse + " milliseconds");
		}
	}
}
