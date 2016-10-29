package club.charleyking.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;

import club.charleyking.gui.MainFrame;
import club.charleyking.util.About;
import club.charleyking.util.Files;
import club.charleyking.util.Find;

public class ButtonAndItemListener implements ActionListener {
	public MainFrame frame;
	public Find find;
	public About about;
	
	public ButtonAndItemListener(MainFrame frame) {
		this.frame = frame;
	}
	
	@Override 
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == frame.itemNewFile || event.getSource() == frame.newButton ) {
			frame.textArea.setText("");
			frame.setTitle("new file");
		} else if (event.getSource() == frame.itemOpenFile || event.getSource() == frame.openButton) {
			new Files(this).openFile();
		} else if (event.getSource() == frame.itemSaveFile || event.getSource() == frame.saveButton) {
			new Files(this).saveFile();
		} else if (event.getSource() == frame.itemClose || event.getSource() == frame.closeButton) {
			System.exit(0);
		} else if (event.getSource() == frame.itemQuickFind || event.getSource() == frame.quickButton) {
			new Find(frame.textArea);
		} else if (event.getSource() == frame.itemAboutMe || event.getSource() == frame.aboutmeButton) {
			new About().aboutMe();
		} else if (event.getSource() == frame.itemAboutSoftware || event.getSource() == frame.aboutButton) {
			new About().aboutSoftware();
		}
	}
}
