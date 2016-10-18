package club.charleyking.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {
	public static void copyFile(File source, File target) throws IOException {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(source);
			output = new FileOutputStream(target);
			byte[] buffer = new byte[1024];
			int bytesRead = input.read(buffer);
			while (bytesRead > 0) {
				output.write(buffer, 0, bytesRead);
			}
		} finally {
			input.close();
			output.close();
		}
	}
	
	public static int countWordInFile(String fileName, String word) {
		int counter = 0;
		try (FileReader fileReader = new FileReader(fileName)) {
			try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
				String line = null;
				while ((line = bufferedReader.readLine())!=null) {
					int index = -1;
					while (line.length()>=word.length()&&(index=line.indexOf(word))>=0) {
						counter++;
						line = line.substring(index + word.length());
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return counter;
	}
}





