import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

public class Tetris {
	public static void main(String[] args) {
		TetrisFrame frame = new TetrisFrame();
	}
}

class TetrisFrame extends JFrame {
	public TetrisFrame() {
		// init menuBar
		JMenuBar menuBar = new JMenuBar();
		JMenu option = new JMenu("game");
		JMenu help = new JMenu("help");
		JMenuItem newGame = option.add("new game");
		JMenuItem pause = option.add("pause");
		JMenuItem exit = option.add("exit");
		JMenuItem about = help.add("about");
		menuBar.add(option);
		menuBar.add(help);
		// init panel
		TetrisPanel panel = new TetrisPanel();
		panel.setBackground(new Color(182, 255, 147));
		add(panel);
		addKeyListener(panel);
		// default frame config
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setSize(220,275);
		setSize(560, 560);
		setTitle("Charles's Tetris");
		setVisible(true);
		setResizable(false);
	}
} 

class TetrisPanel extends JPanel implements KeyListener {
	private int blockType;
	private int score;
	private int turnState;
	private int x;
	private int y;
	private int i, j, flag;
	private boolean gameover;
	// block already put down x=0-11,y=0-21;
	int[][] map = new int[13][23];

	TetrisPanel() {
		resetMap();
		newFallingBlock();
		initWall();
		Timer timer = new Timer(1000, new TimerListener());
		timer.start();
	}

	public void resetMap() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 22; j++) {
				map[i][j] = 0;
			}
		}
	}

	public void newFallingBlock() {
		blockType = (int) (Math.random() * 1000) % 7;
		turnState = (int) (Math.random() * 1000) % 4;
		x = 4;
		y = 0;
		if (gameover(x, y) == 1) {
			resetMap();
			initWall();
			score = 0;
			JOptionPane.showMessageDialog(null, "GAME OVER");
		}
	}

	// draw weiqiang
	public void initWall() {
		for (int i = 0; i < 12; i++) {
			map[i][21] = 2;  // when map[][] = 2, there is a rect.
		}
		for (int j = 0; j < 22; j++) {
			map[11][j] = 2;
			map[0][j] = 2;
		}
	}

	// draw blocks
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (j = 0; j < 16; j++) {
			if (fallingBlock[blockType][turnState][j] == 1) {
				g.fillRect((j % 4 + x + 1) * 20, (j / 4 + y) * 20, 20, 20);  // draw blocks still in the air, constituded with four squares.
			}
		}
		// draw guding block
		for (j = 0; j < 22; j++) {
			for (i = 0; i < 12; i++) {
				if (map[i][j] == 1) {
					g.fillRect(i * 20, j * 20, 20, 20);
				}  // draw blocks already put down, color black
				g.setColor(Color.RED);
				if (map[i][j] == 2) {
					g.drawRect(i * 20, j * 20, 20, 20);
				}  // draw the wall
			}
		}
		g.drawString("score=" + score, 125, 10);
	}

	// falling block, First group is block type s,z,l,j,i,o,t 7 types.second groupe is rotate times, third group is block juzhen
	private final int fallingBlock[][][] = new int[][][] {
	// i
			{ { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
					{ 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 } },
			// s
			{ { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } },
			// z
			{ { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 } },
			// j
			{ { 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
					{ 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
			// o
			{ { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
			// l
			{ { 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
			// t
			{ { 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 } } };
	

	// rotate
	public void turn() {
		int tempturnState = turnState;
		turnState = (turnState + 1) % 4;
		if (blow(x, y, blockType, turnState) == 1) {
		}
		if (blow(x, y, blockType, turnState) == 0) {
			turnState = tempturnState;
		}
		repaint();
	}

	// turn left
	public void left() {
		if (blow(x - 1, y, blockType, turnState) == 1) {
			x = x - 1;
		}
		;
		repaint();
	}

	// turn right
	public void right() {
		if (blow(x + 1, y, blockType, turnState) == 1) {
			x = x + 1;
		}
		;
		repaint();
	}

	// put down
	public void down() {
		if (blow(x, y + 1, blockType, turnState) == 1) {
			y = y + 1;
			delline();
		}
		;
		if (blow(x, y + 1, blockType, turnState) == 0) {
			add(x, y, blockType, turnState);
			newFallingBlock();
			delline();
		}
		;
		repaint();
	}

	// is this legue
	public int blow(int x, int y, int blockType, int turnState) {
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (((fallingBlock[blockType][turnState][a * 4 + b] == 1) && (map[x
						+ b + 1][y + a] == 1))
						|| ((fallingBlock[blockType][turnState][a * 4 + b] == 1) && (map[x
								+ b + 1][y + a] == 2))) {

					return 0;
				}
			}
		}
		return 1;
	}

	// delete a line
	public void delline() {
		int c = 0;
		for (int b = 0; b < 22; b++) {
			for (int a = 0; a < 12; a++) {
				if (map[a][b] == 1) {

					c = c + 1;
					if (c == 10) {
						score += 10;
						for (int d = b; d > 0; d--) {
							for (int e = 0; e < 11; e++) {
								map[e][d] = map[e][d - 1];

							}
						}
					}
				}
			}
			c = 0;
		}
	}

	// is game over
	public int gameover(int x, int y) {
		if (blow(x, y, blockType, turnState) == 0) {
			return 1;
		}
		return 0;
	}

	// add this to map
	public void add(int x, int y, int blockType, int turnState) {
		int j = 0;
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (map[x + b + 1][y + a] == 0) {
					map[x + b + 1][y + a] = fallingBlock[blockType][turnState][j];
				}
				;
				j++;
			}
		}
	}

	// listen to keyboard
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			down();
			break;
		case KeyEvent.VK_UP:
			turn();
			break;
		case KeyEvent.VK_RIGHT:
			right();
			break;
		case KeyEvent.VK_LEFT:
			left();
			break;
		}

	}

	// of no use
	public void keyReleased(KeyEvent e) {
	}

	// of no use
	public void keyTyped(KeyEvent e) {
	}

	// time listener
	class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			repaint();
			if (blow(x, y + 1, blockType, turnState) == 1) {
				y = y + 1;
				delline();
			}
			;
			if (blow(x, y + 1, blockType, turnState) == 0) {

				if (flag == 1) {
					add(x, y, blockType, turnState);
					delline();
					newFallingBlock();
					flag = 0;
				}
				flag = 1;
			}
			;
		}
	}
}
