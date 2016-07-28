package club.charleyking.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class CreatFile {
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("D:\\charleyking.txt")));
		bw.write("hello, charleyking, I love you!");
		bw.flush();
		bw.close();
	}
}
