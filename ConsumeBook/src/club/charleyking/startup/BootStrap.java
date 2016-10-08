package club.charleyking.startup;

import javax.swing.SwingUtilities;

import club.charleyking.frame.MainFrame;
import club.charleyking.panel.MainPanel;
import club.charleyking.panel.SpendPanel;
import club.charleyking.util.GUIUtil;

public class BootStrap {
	public static void main(String[] args) throws Exception {
		GUIUtil.useLNF();
		SwingUtilities.invokeAndWait(new Runnable() {
			@Override 
			public void run() {
				MainFrame.instance.setVisible(true);
				MainPanel.instance.workingPanel.show(SpendPanel.instance);
			}
		});
	}

}
