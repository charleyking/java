import java.io.*;

public class TestFileStream {
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("temp.txt");
		for (int i=0; i<10; i++) {
			fos.write(i);
		}
		fos.close();
		FileInputStream fis = new FileInputStream("temp.txt");
		int value;
		while ((value = fis.read()) != -1) {
			System.out.print(value);
		}
		fis.close();
	}
}