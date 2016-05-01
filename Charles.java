import java.io.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.math.BigInteger;

//this is the main class to init the Gobang
public class Charles {
	public static void main(String[] args) {
		GameFrame game = new GameFrame();
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setVisible(true);
	}
}


// this class init the frame of Gobang
class GameFrame extends JFrame {
	// construction method of GameFrame. 
	public GameFrame() {
		// init the panel
		final Panel panel = new Panel();
		panel.setBackground(new Color(182, 255, 147));
		panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(panel);
		// init the menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("option");
		JMenuItem menuStart = new JMenuItem("start game");
		menuStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.ResetGame();
				panel.repaint();
			}
		});   // use inner class
		JMenuItem menuExit = new JMenuItem("exit");
		menuExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuBar.add(menu);
		menu.add(menuStart);
		menu.add(menuExit);
		setJMenuBar(menuBar);
		// init the jframe
		setSize(560,560);
		setTitle("Charles's Gobang");
		setResizable(false);
	}
}


class Panel extends JPanel {
	public Panel() {
		this.ResetGame();
	}

	public void ResetGame() {
	}
}


