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
		// init panel
		TetrisPanel panel = new TetrisPanel();
		panel.setBackground(new Color(182, 255, 147));
		add(panel);
		addKeyListener(panel);
		// init menuBar
		JMenuBar menuBar = new JMenuBar();
		JMenu option = new JMenu("game");
		JMenu help = new JMenu("help");
		JMenuItem newGame = option.add("new game");
		JMenuItem exit = option.add("exit");
		JMenuItem about = help.add("about");
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.resetMap();
				panel.repaint();
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuBar.add(option);
		menuBar.add(help);
		setJMenuBar(menuBar);
		// little component - buttons and lebles
		// JLable scoreLable = new JLable("");
		// default frame config
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	private int fallingBlockX;
	private int fallingBlockY;
	private int flag;
	// block already put down x=0-11,y=0-21; if map == 0, there is blank, eles if map == 1, there is square that already fell down
	// else if map == 2, there is square make the wall
	private int[][] map = new int[13][23];
	// falling block, First group is block type s,z,l,j,i,o,t 7 types.second groupe is rotate state, 4 types.
	// third group is block juzhen, every block is constitude of 4*4 = 16 squares.
	// if fallingBlock == 1, there is a square, else if fallingBlock == 0, there is not a square.
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


    // constructure method
	TetrisPanel() {
		//JButton scoreLabel = new JButton("hello");
		//add(scoreLabel);
		resetMap();
		Timer timer = new Timer(1000, new TimerListener());
		timer.start();
	}

	public void resetMap() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 22; j++) {
				map[i][j] = 0;
			}
		}
		score = 0;
		initWall();
		newFallingBlock();
		repaint();
	}

	// draw weiqiang
	public void initWall() {
		for (int i = 0; i < 12; i++) {
			map[i][21] = 2;  // when map[][] = 2, there is a square.
		}
		for (int j = 0; j < 22; j++) {
			map[11][j] = 2;
			map[0][j] = 2;
		}
	}

	public void newFallingBlock() {
		blockType = (int) (Math.random() * 1000) % 7;
		turnState = (int) (Math.random() * 1000) % 4;
		fallingBlockX = 4;
		fallingBlockY = 0;
		gameOver(fallingBlockX, fallingBlockY);
	}

	// is game over
	public void gameOver(int x, int y) {
		if (!isOk(x, y, blockType, turnState)) {
			resetMap();
			initWall();
			JOptionPane.showMessageDialog(null, "GAME OVER");
		}
	}

	// to judge is this operation legue or not
	public boolean isOk(int fallingBlockX, int fallingBlockY, int blockType, int turnState) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (((fallingBlock[blockType][turnState][i * 4 + j] == 1) && (map[fallingBlockX + j + 1][fallingBlockY + i] == 1))
					|| ((fallingBlock[blockType][turnState][i * 4 + j] == 1) && (map[fallingBlockX + j + 1][fallingBlockY + i] ==2))) {
					return false;
				}
			}
		}
		return true;
	}

	// draw blocks
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		switch (blockType) {
			case 0:
				g.setColor(Color.yellow);
				break;
			case 1:
				g.setColor(Color.green);
				break;
			case 2:
				g.setColor(Color.blue);
				break;
			case 3:
				g.setColor(Color.red);
				break;
			case 4:
				g.setColor(Color.cyan);
				break;
			case 5:
				g.setColor(Color.pink);
				break;
			case 6:
				g.setColor(Color.orange);
				break;
		}
		for (int i = 0; i < 16; i++) {
			if (fallingBlock[blockType][turnState][i] == 1) {
				g.fillRect((i % 4 + fallingBlockX + 1) * 20 + 160, (i / 4 + fallingBlockY) * 20, 20, 20);  
				// draw blocks still in the air, constituded with four squares.
				// add 160 to move the whole map to the middle of panel
			}
		}
		// draw block already fell down and the wall
		for (int j = 0; j < 22; j++) {
			for (int i = 0; i < 12; i++) {
				if (map[i][j] == 1) {
					g.fillRect(i * 20 + 160, j * 20, 20, 20);
				}  // draw blocks already put down, color black
				g.setColor(Color.RED);
				if (map[i][j] == 2) {
					g.drawRect(i * 20 + 160, j * 20, 20, 20);
				}  // draw the wall
			}
		}
		g.drawString("score=" + score, 125, 10);
	}

	// rotate
	public void turn() {
		int tempturnState = turnState;
		turnState = (turnState + 1) % 4;
		if (!isOk(fallingBlockX, fallingBlockY, blockType, turnState)) {
			turnState = tempturnState;
		}
		repaint();
	}

	// turn left
	public void left() {
		if (isOk(fallingBlockX - 1, fallingBlockY, blockType, turnState)) {
			fallingBlockX = fallingBlockX - 1;
		}
		repaint();
	}

	// turn right
	public void right() {
		if (isOk(fallingBlockX + 1, fallingBlockY, blockType, turnState)) {
			fallingBlockX = fallingBlockX + 1;
		}
		repaint();
	}

	// put down
	public void down() {
		if (isOk(fallingBlockX, fallingBlockY + 1, blockType, turnState)) {
			fallingBlockY = fallingBlockY + 1;
		} else if (!isOk(fallingBlockX, fallingBlockY + 1, blockType, turnState)) {
			add(fallingBlockX, fallingBlockY, blockType, turnState);
			deleteLine();
			newFallingBlock();
		}
		repaint();
	}

	// delete a line
	public void deleteLine() {
		int count = 0;
		for (int j = 0; j < 22; j++) {
			for (int i = 0; i < 12; i++) {
				if (map[i][j] == 1) {
					count ++;
					if (count == 10) {
						score += 100;
						for (int y = j; y > 0; y--) {
							for (int x = 0; x < 11; x++) {
								map[x][y] = map[x][y - 1];
							}
						}
					}
				}
			}
			count = 0;
		}
	}

	// add this to map
	public void add(int fallingBlockX, int fallingBlockY, int blockType, int turnState) {
		int j = 0;
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (map[fallingBlockX + b + 1][fallingBlockY + a] == 0) {
					map[fallingBlockX + b + 1][fallingBlockY + a] = fallingBlock[blockType][turnState][j];
				}
				j ++;
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
			if (isOk(fallingBlockX, fallingBlockY + 1, blockType, turnState)) {
				fallingBlockY = fallingBlockY + 1;
			} else if (!isOk(fallingBlockX, fallingBlockY + 1, blockType, turnState)) {
				add(fallingBlockX, fallingBlockY, blockType, turnState);
				deleteLine();
				newFallingBlock();
			}
		}
	}
}
