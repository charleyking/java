// hello world instance with spring


package club.charleyking.study;

public class HelloWorld {
	private String message;
	
	public void setMessage(String message) {
		this.message = message;
	}
	public void getMessage() {
		System.out.println("your message is " + message);
	}
}