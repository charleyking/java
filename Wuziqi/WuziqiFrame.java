//package club.charleyking.wuziqi.frame;

import java.awt.*;
import javax.swing.*;

public class WuziqiFrame extends JFrame {
	// get screen width & height 
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	// save chess scale
	int x = 0;
	int y = 0;
	//save all chess's scall, no chess is 0, black is 1, white is 2
	int[][] allChess = new int[19][19];
	// flag next step is black or white
	boolean isBlack = true;
	// flag present game can play or not
	boolean canPlay = true;
	// save showed message
	String message = "black first";
	// save max time remained(sec)
	int maxTime = 0;
	// save black and white remained time
	int blackTime = 0;
	int whiteTime = 0;
	// save both side remained time
	String blackMessage = "no limit";
	String whiteMessage = "no limit";

	public WuziqiFrame() throws HeadlessException {
		this.setTitle("wuziqi");
		this.setSize(500, 500);
		this.setLocation((width-500)/2, (height-500)/2);
		this.setResizable(false);
		// add listener
		this.addMouseListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void mouseClickeed(MouseEvent e) {
		JOptionPane.showMessageDialog(this, "mouse click");
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}
}
