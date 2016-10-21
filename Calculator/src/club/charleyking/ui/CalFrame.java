package club.charleyking.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalFrame extends JFrame {
	private String[] buttonName = {"1","2","3","4","5","6","7","8","9","0","+","-","*","/","="};
	private JTextField textField;
	private JPanel panel;
	
	public CalFrame() {
		panel = new JPanel();
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(200, 40));
		textField.setHorizontalAlignment(JTextField.RIGHT);
		panel.setLayout(new GridLayout(5,4,20,20));
		panel.setBorder(BorderFactory.createLineBorder(Color.pink));
		for (int i=0; i<buttonName.length; i++) {
			panel.add(new JButton(buttonName[i]));
		}
		this.setLayout(new BorderLayout());
		this.getContentPane().add(panel, BorderLayout.CENTER);
		this.getContentPane().add(textField, BorderLayout.NORTH);
		this.init();
	}
	
	public void init() {
		this.setSize(500, 500);
		this.setTitle("calculator");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		CalFrame calculator = new CalFrame();
	}
}
