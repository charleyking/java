import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ChessFrame extends JFrame {
	private ChessBoard chessBoard;
	private JPanel toolbar;
	private JButton startButton;
	private JButton backButton;
	private JButton exitButton;
	private JMenuBar menuBar;
	private JMenu option;
	private JMenuItem startMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem backMenuItem;

	public static void main(String[] args) {
		ChessFrame frame = new ChessFrame();
		frame.setVisible(true);
	}

	public ChessFrame() {
		setTitle("Gobang");
		chessBoard = new ChessBoard();
		menuBar = new JMenuBar();
		option = new JMenu("option");
		startMenuItem = new JMenuItem("start");
		exitMenuItem = new JMenuItem("exit");
		backMenuItem = new JMenuItem("back");
		option.add(startMenuItem);
		option.add(exitMenuItem);
		option.add(backMenuItem);
		MyActionListener myActionListener = new MyActionListener();
		startMenuItem.addActionListener(myActionListener);
		backMenuItem.addActionListener(myActionListener);
		exitMenuItem.addActionListener(myActionListener);
		menuBar.add(option);
		setJMenuBar(menuBar);
		toolbar = new JPanel();
		startButton = new JButton("restart");
		backButton = new JButton("back");
		exitButton = new JButton("exit");
		toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolbar.add(startButton);
		toolbar.add(exitButton);
		toolbar.add(backButton);
		startButton.addActionListener(myActionListener);
		exitButton.addActionListener(myActionListener);
		backButton.addActionListener(myActionListener);
		add(toolbar,BorderLayout.NORTH);
		add(chessBoard);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,650);
		//pack();
	}

	private class MyActionListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			Object obj = e.getSource();
			if (obj == ChessFrame.this.startMenuItem || obj == startButton) {
				chessBoard.restartGame();
			} else if (obj == exitMenuItem || obj == exitButton) {
				System.exit(0);
			} else if (obj == backMenuItem || obj == backButton) {
				chessBoard.goBack();
			}
		}
	}
}