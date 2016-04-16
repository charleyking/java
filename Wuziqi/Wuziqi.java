import javax.swing.*;
import java.awt.Toolkit;

public class Wuziqi {
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.setTitle("d");
		jf.setSize(200,100);
		//jf.setLocation(200,100);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		System.out.println("width: " + width);
		System.out.println("height: " + height);
		jf.setLocation((width-200)/2, (height-200)/2);
	}
}