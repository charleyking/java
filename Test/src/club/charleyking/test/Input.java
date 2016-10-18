package club.charleyking.test;

import javax.swing.JOptionPane;

public class Input {
	String name;
	public void inputInfo() {
		name = JOptionPane.showInputDialog(null);
		JOptionPane.showMessageDialog(null, name);
	}
	public static void main(String[] args) {
		Input a = new Input();
		a.inputInfo();
	}
}
