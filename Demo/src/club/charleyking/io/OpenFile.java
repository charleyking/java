package club.charleyking.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class OpenFile {
	public static void main(String[] args) throws IOException {
		try {
			File f = new File("D:\\charleyking.txt");
			FileInputStream fis = new FileInputStream(f);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			System.out.println(br.readLine());
			System.out.println(f.getAbsolutePath());
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
	}
}
