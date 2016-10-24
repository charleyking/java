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
		if (e.getActionCommand().equals("clear")) {
			frame.textField.setText("");
			frame.sb.setLength(0);
		} else {
			String action = e.getActionCommand();
			frame.sb.append(action);
			frame.textField.setText(frame.sb.toString());
		}
	}
}
