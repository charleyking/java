package club.charleyking.test;

public class Test {
	public static void main(String[] args) {
		Son son = new Son();
		System.out.println(son.getAge());
	}
	
}

class Father {
	public int age;
	public String name;
	public Father() {
		System.out.println("i am father");
	}
	public int getAge() {
		return this.age;
	}
	public String getName() {
		return this.name;
	}
	
	static {
		System.out.println("static in father");
	}
}

class Son extends Father {
	public String sex;
	public Son() {
		System.out.println("i am son");
	}
	static {
		System.out.println("static in son");
	}
}