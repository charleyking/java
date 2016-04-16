import java.io.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math;

public class Charles {
	public static void main(String[] args) throws Exception {
		System.out.println("Please input an integer between 1 and 9");
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();
		for (int i=1; i<=num; i++) {
			for (int j=1; j<=num-i; j++) {
				System.out.print("\t");
			}
			for (int k=1; k<=i; k++) {
				System.out.print(Integer.parseInt(Math.pow(2, k-1)) + "\t");
			}
			for (int m=i-2; m>=0; m--) {
				System.out.print(Math.pow(2, m) + "\t");
			}
			System.out.println("");
		}
	}
}