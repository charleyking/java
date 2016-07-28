package club.charleyking.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MyTest {
	@Test
	public void listtest() {
		List<Student> alist = new ArrayList<Student>();
		for (int i = 0; i <= 3; i++) {
			alist.add(new Student("charles", i));
		}
		Iterator<Student> it = alist.iterator();
		while (it.hasNext()) {
			System.out.println("iterator " + it.next());
		}
		for (int i = 0; i <= 3; i++) {
			System.out.println("for " + alist.get(i));
		}
		for (Student stu: alist) {
			System.out.println("enhanced for " + stu);
		}
	}
	
}
