/*Eight queens is a classic problem that can use recurse to solve it*/

import java.awt.*;
import javax.swing.*;

public class EightQueens {
	static final int MAXROW = 8;
	static int oktimes = 0;
	static int[] queens = new int[MAXROW];

	public static void main(String[] args) {
		for (int i=0; i<MAXROW; i++) {
			queens[i] = -1;
		}
		placequeen(0);
		System.out.println(" \n EightQueens have " + oktimes + " solutions.");
	}

	public static void placequeen(int row) {
		int i = 0;
		boolean[] qsave = new boolean[MAXROW];
		for (; i<MAXROW; i++) {
			qsave[i] = true;
		}

		i = 0;
		while (i<row) {
			qsave[queens[i]] = false;
			int k = row - i;
			if ((queens[i] + k >= 0) && (queens[i] + k < MAXROW)) {
				qsave[queens[i]+k] = false;
			}
			if ((queens[i] - k >=0) && (queens[i] - k < MAXROW)) {
				qsave[queens[i]-k] = false;
			}
			i++;
		}

		//use recursor
		for (i=0; i<MAXROW; i++) {
			if (qsave[i]==false) continue;
			if (row<MAXROW-1) {
				queens[row] = i;
				placequeen(row+1);
			} else {
				queens[row] = i;
				oktimes++;
				System.out.println(" this is the " + oktimes + " solution");
				printOneSolution();
			}
		}
	}

	public static void printOneSolution() {
		for (int i=0; i<MAXROW; i++) {
			String therow = " the " + (i+1) + " line:";
			if (queens[i]!=0) {
				for(int j=0; j<queens[i]; j++) {
					therow+=" --";
				}
				therow+=" ++";
				int j = queens[i];
				while (j<MAXROW-1) {
					therow+=" --";
					j++;
				}
				System.out.println(therow);
			}
		}
	}
}