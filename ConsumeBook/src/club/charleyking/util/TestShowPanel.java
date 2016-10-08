package club.charleyking.util;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TestShowPanel {
	public static void main(String[] args) {
		GUIUtil.useLNF();
		JPanel jp = new JPanel();
		jp.add(new JButton("button_1"));
		jp.add(new JButton("button_2"));
		GUIUtil.showPanel(jp);
	}
}
