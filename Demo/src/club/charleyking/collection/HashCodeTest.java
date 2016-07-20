package club.charleyking.collection;

public class HashCodeTest {
	public static void main(String[] args) {
		Person charles = new Person("charles", 22);
		Person tom = new Person("tom", 23);
		int b = tom.hashCode();
		int a = charles.hashCode();
		System.out.println(a + "\n" + b);
	}
}

class Person {
	public String name;
	public int age;
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
		System.out.println("name is " + name + " and age is " + age);
	}
}