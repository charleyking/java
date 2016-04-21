import java.io.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math;
import java.awt.Graphics;

public class Charles {
	public static void main(String[] args) {
		JFrame jf = new JFrame("interesting");
		MyPanel mp = new MyPanel();
		JButton jb = new JButton();
		mp.add(jb);
		jf.add(mp);
		jf.setSize(200, 200);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}

class MyPanel extends JPanel {
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawLine(30,30,60,60);
		g.drawString("hello", 40,40);
	}
}