package club.charleyking.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Test {
	public static void main(String[] args) throws IOException {
		IOUtil.copyFile("D:\\charleyking.txt", "D:\\wangjiahao.txt");
	}
}
