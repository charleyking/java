package club.charleyking.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import club.charleyking.listener.ButtonListener;
import club.charleyking.listener.OperationListener;

public class CalFrame extends JFrame {
	public String[] buttonName = {"0","1","2","3","4","5","6","7","8","9"};
	public JButton[] buttons;
	public JTextField textField;
	public JPanel panel, textPanel;
	public Border emptyEdge, blackline;
	public JButton button, buttonAdd, buttonSub, buttonTime, buttonDivide, buttonEqual, buttonClear;
	public ActionListener listener;
	public int operation, firstNumber, secondNumber, result;
	public StringBuilder sb;
	
	public CalFrame() {
		sb = new StringBuilder();
		panel = new JPanel();
		//textPanel = new JPanel();
		textField = new JTextField();
		buttons = new JButton[10];
		blackline = BorderFactory.createLineBorder(Color.black);
		emptyEdge = BorderFactory.createEmptyBorder(40,10,10,10);
		textField.setPreferredSize(new Dimension(0, 60));
		textField.setHorizontalAlignment(JTextField.RIGHT);
		textField.setFont(new Font("Serif", Font.BOLD, 23));
		textField.setEditable(false);
		//textPanel.add(textField);
		textField.setBorder(blackline);
		panel.setLayout(new GridLayout(5,5,10,30));
		panel.setBorder(emptyEdge);
		for (int i=0; i<10; i++) {
			addButton(panel, String.valueOf(i));
		}
		addOperationButton(panel, "+", 1);
		addOperationButton(panel, "-", 2);
		addOperationButton(panel, "*", 3);
		addOperationButton(panel, "/", 4);
		buttonEqual = new JButton("=");
		buttonEqual.setActionCommand("=");
		buttonEqual.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				secondNumber = Integer.parseInt(textField.getText());
				textField.setText("=");
				if (operation == 1) {
					result = firstNumber + secondNumber;
				} else if (operation == 2) {
					result = firstNumber - secondNumber;
				} else if (operation == 3) {
					result = firstNumber * secondNumber;
				} else if (operation == 4) {
					result = firstNumber / secondNumber;
				}
				textField.setText(String.valueOf(result));
				sb.setLength(0);
			}
		});	
		panel.add(buttonEqual);
		addButton(panel, "clear");	
		this.getContentPane().add(panel, BorderLayout.CENTER);
		this.getContentPane().add(textField, BorderLayout.NORTH);
		this.init();
	}
	
	public void addOperationButton(JPanel panel, String name, int operation) {
		button = new JButton(name);
		listener = new OperationListener(this, operation);
		button.setActionCommand(name);
		button.addActionListener(listener);
		panel.add(button);
	}
	
	public void addButton(JPanel panel, String name) {
		button = new JButton(name);
		listener = new ButtonListener(this);
		button.setActionCommand(name);
		button.addActionListener(listener);
		panel.add(button);
	}
	
	public void init() {
		this.setSize(500, 500);
		this.setTitle("calculator");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
}
