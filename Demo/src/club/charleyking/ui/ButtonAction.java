package club.charleyking.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ButtonAction extends JFrame implements ActionListener {
	public JButton button;
	public JTextField textField;
	public ButtonAction() {
		button = new JButton("start on cons");
		button.setName("start");
		button.setActionCommand("command");
		button.addActionListener(this);
		textField = new JTextField();
		this.add(textField,BorderLayout.NORTH);
		this.add(button,BorderLayout.CENTER);
		init();
		System.out.println(button.getActionCommand());
	}
	public void init() {
		this.setTitle("action test");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(400, 500);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//textField.setText(button.getName());
		//button.setName("change");
		if (e.getActionCommand().equals("command")){
			//JOptionPane.showMessageDialog(null, button.getName());
			textField.setText(button.getName());
		}
	}
	public static void main(String[] args) {
		ButtonAction buttonAction = new ButtonAction();
	}
}
