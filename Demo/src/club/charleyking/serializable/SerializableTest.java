package club.charleyking.serializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableTest {
	public static void main(String[] args) {
		User charleyking = new User();
		charleyking.setName("charleyking");
		charleyking.setPass("888");
		System.out.println("before serializable");
		System.out.println("name is " + charleyking.getName());
		System.out.println("password is " + charleyking.getPass());
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("D:\\charles.txt"));
			os.writeObject(charleyking);
			os.flush();
			os.close();
			System.out.println("hello, this is charles");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("D:\\charles.txt"));
			User charles = (User)is.readObject();
			is.close();
			System.out.println("after serializable");
			System.out.println(charles.getName());
			System.out.println(charles.getPass());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
