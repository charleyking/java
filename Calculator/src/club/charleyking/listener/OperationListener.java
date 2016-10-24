package club.charleyking.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import club.charleyking.ui.CalFrame;

public class OperationListener implements ActionListener{
	public CalFrame frame;
	int operation;
	public OperationListener(CalFrame frame, int operation) {
		this.frame = frame;
		this.operation = operation;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		String stringtest = frame.textField.getText();
		frame.firstNumber = Integer.parseInt(stringtest);
		//System.out.println(frame.firstNumber);
		//frame.firstNumber = Integer.parseInt(frame.textField.getText());
		frame.textField.setText(action);
		frame.operation = operation;
		//System.out.println("operation" + frame.operation);
	}
}
