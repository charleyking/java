package club.charleyking.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JLabelTest extends JFrame {
	JPanel panel = new JPanel();
	JButton button = new JButton("hei");
	public JLabelTest() {
		button.setSize(100, 300);
		button.setText("fuck");
		int height = button.getHeight();
		System.out.println(height);
		panel.add(button);
	}
	
	public void init() {
		this.add(panel);
		this.setTitle("label test");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		JLabelTest test = new JLabelTest();
		test.init();
	}
}
