package club.charleyking.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class IOUtil {
	// Util class have static method, so it's a good habit to private the constructor.
	private IOUtil() {
		throw new AssertionError();
	}
	
	public static void copyFile(String sourceFile, String targetFile) throws IOException {
		File file = new File(sourceFile);
		File file2 = new File(targetFile);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2)));
		String input = br.readLine();
		bw.write(input);
		bw.flush();
		bw.close();
		br.close();
	}		
	
	/* List all files name in a specific directory */
	public static void listFileNames(String directory){
		File file = new File(directory);
		for (File temp: file.listFiles()) {
			if (temp.isFile()) {
				System.out.println(temp.getName());
			}
		}
	}
	
	/* List all files name in a specific directory and subdirectory */
	public static void listAllFileNames(String directory) {
		File file = new File(directory);
		for (File temp: file.listFiles()) {
			if (temp.isFile()) {
				System.out.println(temp.getName());
			} else {
				listAllFileNames(temp.getPath());
			}
		}
	}
	
	public static void listMaxFile(String directory) {
		String name = "";
		long size = 0;
		long maxFile = 0;
		File file = new File(directory);
		for (File f: file.listFiles()) {
			if (f.isFile()) {
			if (f.length()>maxFile) {
				maxFile = f.length();
				name = f.getAbsolutePath();
				size = f.getTotalSpace();
			}
			}
		}
		System.out.println("the max file name is " + name + " and the size is " + size);
	}
}
