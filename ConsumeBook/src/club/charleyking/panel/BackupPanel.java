package club.charleyking.panel;

import javax.swing.JButton;
import javax.swing.JPanel;

import club.charleyking.listener.BackupListener;
import club.charleyking.util.ColorUtil;
import club.charleyking.util.GUIUtil;

public class BackupPanel extends WorkingPanel {
	static {
		GUIUtil.useLNF();
	}
	public static BackupPanel instance = new BackupPanel();
	JButton b_backup = new JButton("backup");
	
	public BackupPanel() {
		GUIUtil.setColor(ColorUtil.blueColor, b_backup);
		this.add(b_backup);
	}
	
	@Override
	public void updateData() {
		
	}
	
	@Override
	public void addListener() {
		BackupListener listener = new BackupListener();
		b_backup.addActionListener(listener);
	}
	public static void main(String[] args) {
		GUIUtil.showPanel(BackupPanel.instance);
	}
}
