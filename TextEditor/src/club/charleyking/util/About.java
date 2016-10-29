package club.charleyking.util;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class About extends JFrame{
	public JPanel panel;
	public JLabel label;
	public String aboutString;
	public About() {
		panel = new JPanel();
		label = new JLabel();
		panel.add(label);
		this.add(panel);
		this.setSize(300, 200);
		this.setResizable(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void aboutMe() {
		aboutString = "<html><body><p>" +
				"Author:" + TextEditorInfo.AUTHOR + "<br />" +
				"Contact me at: " +
				"<a href='mailto:" + TextEditorInfo.EMAIL + "?subject=About the NotePad Software'>" + TextEditorInfo.EMAIL + "</a>" +
		                "<br /><br /><br />"  + "</p></body></html>";
		label.setText(aboutString);		
	}
	
	public void aboutSoftware() {
		aboutString = "<html><body><p>" +		
				"Name: " + TextEditorInfo.NAME + "<br />" +
				"Version: " + TextEditorInfo.VERSION + 
				"</p></body></html>";
		label.setText(aboutString);
	}
}
