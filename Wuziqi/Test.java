import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Test {
	public static void main(String[] args) {
		WuziqiFrame wf = new WuziqiFrame();
		JOptionPane.showMessageDialog(wf, "hello");
		int result = JOptionPane.showConfirmDialog(wf, "confirm dialog");
		if (result == 0) {
			JOptionPane.showMessageDialog(wf, "game begin");
		}
		if (result == 1) {
			JOptionPane.showMessageDialog(wf, "game exit");
		}
		if (result == 2) {
			JOptionPane.showMessageDialog(wf, "please try again");
		}
		String username = JOptionPane.showInputDialog("Please input your name: ");
		if (username != null) {
			System.out.println(username);
			JOptionPane.showMessageDialog(wf, "Input name is: " + username);

		} else {
			JOptionPane.showMessageDialog(wf, "Please input your message again");
		}

	}	

}