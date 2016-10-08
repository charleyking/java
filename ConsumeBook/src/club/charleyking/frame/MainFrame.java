package club.charleyking.frame;

import javax.swing.JFrame;

import club.charleyking.panel.MainPanel;

public class MainFrame extends JFrame {
	public static MainFrame instance = new MainFrame();
	
	private MainFrame() {
		this.setSize(500, 400);
		this.setTitle("a bill book");
		this.setContentPane(MainPanel.instance);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		instance.setVisible(true);
	}
}
