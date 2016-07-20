package club.charleyking.collection;

public class Student implements Comparable<Student>{
	private String name;
	private int age;
	
	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	@Override 
	public String toString() {
		return "name is: " + name + " and age is " + age;
	}
	
	@Override 
	public int compareTo(Student o) {
		return this.age - o.age;
	}
}
