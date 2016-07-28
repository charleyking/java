package club.charleyking.collection;

public class Student implements Comparable<Student>{
	private String name;
	private int age;
	
	public Student() {
		
	}
	
	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
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
