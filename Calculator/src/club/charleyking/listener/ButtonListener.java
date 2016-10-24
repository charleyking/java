package club.charleyking.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import club.charleyking.ui.CalFrame;

public class ButtonListener implements ActionListener {
	public CalFrame frame;
	public ButtonListener(CalFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		frame.textField.setText(action);
	}
}
