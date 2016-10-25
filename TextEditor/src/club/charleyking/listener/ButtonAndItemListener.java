package club.charleyking.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JFileChooser;

import club.charleyking.gui.MainFrame;

public class ButtonAndItemListener implements ActionListener {
	MainFrame frame;
	
	public ButtonAndItemListener(MainFrame frame) {
		this.frame = frame;
	}
	
	@Override 
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == frame.itemNewFile || event.getSource() == frame.newButton ) {
			frame.textArea.setText("");
		} else if (event.getSource() == frame.itemOpenFile || event.getSource() == frame.openButton) {
			JFileChooser fileChooser = new JFileChooser();
			int option = fileChooser.showOpenDialog(frame);
			/*
			 * NOTE: because we are OPENing a file, we call showOpenDialog~ if
			 * the user clicked OK, we have "APPROVE_OPTION" so we want to open
			 * the file
			 */
			if (option == JFileChooser.APPROVE_OPTION) {
				frame.textArea.setText("");
				try {
					Scanner scanner = new Scanner(new FileReader(fileChooser.getSelectedFile().getPath()));
					if (scanner.hasNext()) {
						frame.textArea.append(scanner.nextLine() + "\n");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}
