package club.charleyking.interview;

public class Car {
	public String name  = "charles";
	public static void main(String[] args) {
		new Car().run();
		System.out.println(new Car().name);
	}
	
	public final void run() {
		System.out.println("car");
	}
}
