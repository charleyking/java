package club.charleyking.collection;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetTest {
	public static void main(String[] args) {
		Set<Student> students = new TreeSet<>();
		students.add(new Student("aaa", 55));
		students.add(new Student("bbb", 22));
		students.add(new Student("ccc", 33));
		students.add(new Student("ddd", 44));
		
		for(Student stu: students) {
			System.out.println(stu);
		}
	}
}
