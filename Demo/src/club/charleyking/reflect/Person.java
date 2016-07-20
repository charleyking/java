package club.charleyking.reflect;

import java.util.List;

public class Person {
	public String name;
	public int age;
	private int password;
	public List<String> hobbies;
	
	public Person(){
		System.out.println("constructor 1");
	}
	public Person(String name, int age) {
		this.age = age;
		this.name = name;
		System.out.println(name + " is " + age + " years old.");
	}
	
	public void speak() {
		System.out.println("who are you?");
	}
	public void speak(String name, int age) {
		System.out.println(name + age);
	}
	public String getName() {
		return this.name;
	}
	public int getAge() {
		return this.age;
	}
	public int getPassword() {
		return this.password;
	}
}
