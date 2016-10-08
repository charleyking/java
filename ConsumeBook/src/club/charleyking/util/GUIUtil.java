package club.charleyking.util;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIUtil {
	private static String imagePath = "d:/cs/java/ConsumeBook/img";
	public static void setImgIcon(JButton jb, String fileName, String tip) {
		ImageIcon imgIcon = new ImageIcon(new File(imagePath, fileName).getAbsolutePath());
		jb.setIcon(imgIcon);
		jb.setPreferredSize(new Dimension(60, 80));
		jb.setToolTipText(tip);
		jb.setVerticalTextPosition(JButton.BOTTOM);
		jb.setHorizontalTextPosition(JButton.CENTER);
		jb.setText(tip);
	}
	
	public static void setColor(Color color, JComponent...components) {
		for (JComponent compo: components) {
			compo.setForeground(color);
		}
	}
	
	public static void showPanel(JPanel jp, double strechRate) {
		GUIUtil.useLNF();
		JFrame jf = new JFrame();
		jf.setSize(500, 500);
		jf.setLocationRelativeTo(null);
		CenterPanel cp = new CenterPanel(strechRate);
		jf.setContentPane(cp);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		cp.show(jp);
	}
	
	public static void showPanel(JPanel jp) {
		showPanel(jp, 0.85);
	}
	
	public static boolean checkNumber(JTextField jtf, String input) {
		if (!checkEmpty(jtf, input)) {
			return false;
		}
		String text = jtf.getText().trim();
		try {
			Integer.parseInt(text);
			return true;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, input + "need to be integer");
			jtf.grabFocus();
			return false;
		}
	}
	
	public static boolean checkZero(JTextField jtf, String input) {
		if (!checkNumber(jtf, input)) {
			return false;
		}
		String text = jtf.getText().trim();
		if (0 == Integer.parseInt(text)) {
			JOptionPane.showMessageDialog(null, input + "can not be 0");
			jtf.grabFocus();
			return false;
		}
		return true;
	}
	
	public static boolean checkEmpty(JTextField jtf, String input) {
		String text = jtf.getText().trim();
		if (0 == text.length()) {
			JOptionPane.showMessageDialog(null, input + "can not be null");
			jtf.grabFocus();
			return false;
		}
		return true;
	}
	
	public static int getInt(JTextField jtf) {
		return Integer.parseInt(jtf.getText());
	}
	
	public static void useLNF() {
		try {
			javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
