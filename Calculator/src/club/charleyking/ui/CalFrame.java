package club.charleyking.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalFrame extends JFrame {
	private String[] buttonName = {"1","2","3","4","5","6","7","8","9","+","-","*","/","="};
	private JLabel label;
	private JPanel panel;
	
	public CalFrame() {
		panel = new JPanel();
		label = new JLabel("hello");
		label.setBounds(0, 0, 100, 200);
		panel.setLayout(new GridLayout(4,4));
		for (int i=0; i<buttonName.length; i++) {
			panel.add(new JButton(buttonName[i]));
		}
		this.setLayout(new BorderLayout());
		this.getContentPane().add(panel, BorderLayout.CENTER);
		this.getContentPane().add(label, BorderLayout.NORTH);
		this.setSize(500, 500);
		this.setTitle("calculator");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		CalFrame calculator = new CalFrame();
	}
}
