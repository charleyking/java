package club.charleyking.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;

import club.charleyking.gui.MainFrame;
import club.charleyking.listener.ButtonAndItemListener;

public class Files {
	public ButtonAndItemListener listener;
	
	public Files(ButtonAndItemListener listener) {
		this.listener = listener;
	}
	
	public void openFile() {
		JFileChooser fileChooser = new JFileChooser();
		int option = fileChooser.showOpenDialog(listener.frame);
		/*
		 * NOTE: because we are OPENing a file, we call showOpenDialog~ if
		 * the user clicked OK, we have "APPROVE_OPTION" so we want to open
		 * the file
		 */
		try {
			Scanner scanner = new Scanner(new FileReader(fileChooser.getSelectedFile().getPath()));
			if (scanner.hasNext()) {
				listener.frame.textArea.append(scanner.nextLine() + "\n");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + " can not open");
		}
	}
	
	public void saveFile() {
		JFileChooser fileChooser = new JFileChooser();
		int option = fileChooser.showSaveDialog(listener.frame);
		if (option == JFileChooser.APPROVE_OPTION) {
			try {
				File file = fileChooser.getSelectedFile();
				listener.frame.setTitle(file.getName());
				BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
				writer.write(listener.frame.textArea.getText());
				writer.close();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		} 
	}
}
