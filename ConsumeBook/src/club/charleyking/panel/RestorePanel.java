package club.charleyking.panel;

import javax.swing.JButton;
import javax.swing.JPanel;

import club.charleyking.listener.RecoverListener;
import club.charleyking.util.ColorUtil;
import club.charleyking.util.GUIUtil;

public class RestorePanel extends WorkingPanel {
	static {
		GUIUtil.useLNF();
	}
	public static RestorePanel instance = new RestorePanel();
	JButton b_restore = new JButton("restore");
	
	public RestorePanel() {
		GUIUtil.setColor(ColorUtil.blueColor, b_restore);
		this.add(b_restore);
	}
	
	@Override
	public void updateData() {
		
	}
	
	@Override
	public void addListener() {
		RecoverListener listener = new RecoverListener();
		b_restore.addActionListener(listener);
	}
	
	public static void main(String[] args) {
		GUIUtil.showPanel(RestorePanel.instance);
	}
}
