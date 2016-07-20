package club.charleyking.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtil {
	// Util class have static method, so it's a good habit to private the constructor.
	private IOUtil() {
		throw new AssertionError();
	}
	
	public static void copyFile(String source, String target) {
		
	}		
	
	/* List all file names in a specific directory */
	public static void listAllFileNames(String directory){
		File file = new File(directory);
		for (File temp: file.listFiles()) {
			if (temp.isFile()) {
				System.out.println(temp.getName());
			}
		}
	}
}
