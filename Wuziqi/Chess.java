import java.io.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

//this is the main class to init the Gobang
public class Chess {
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
		setSize(550,600);
		setTitle("Charles's Gobang");
		setResizable(false);
	}
}


class Panel extends JPanel {
	// variable fields -- 
	private int i,j,k,icount;
	private int chessmanX, chessmanY;  // chessmanX and chessmanY vary from 0 to 15
	private int boardX, boardY;  // boardX and boardY vary from 50 to 500
	private int pcount, ccount; // number of chess for each side
	private int[][] board = new int[16][16];  // player(0), computer(1), none(2)
	private int[][] cgrades = new int[16][16];
	private int[][] pgrades = new int[16][16];
	private int cgrade, pgrade;
	private int[][] win = new int[2][672]; // number of chess in one line. play(0), computer(1)
	private int bout = 1;
	private int mat, nat, mde, nde;
	private boolean player, computer, over, pwin, cwin, tie, start;
	private boolean[][][] ptable = new boolean[16][16][672];
	private boolean[][][] ctable = new boolean[16][16][672];

	// construct method
	public Panel() {
		addMouseListener(new MouseClick());
		this.ResetGame();
	}

	// mouse listener, inner class
	class MouseClick extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (!over) {
				boardX = e.getX();
				boardY = e.getY();
				//System.out.println("x = " + boardX + "; y = " + boardY);
				mouseClick();
				repaint();
			}
		}
	}

	// detail operation when mouse clicked
	public void mouseClick() {
		if (!this.over) {
			if (this.player) {  // pay attention to this!!!!#####$$$
				if (this.boardX < 520 && this.boardY < 520) {
					//System.out.println("correct area clicked");
					int m1 = chessmanX, n1 = chessmanY;
					chessmanX = (boardX - 35) / 30;
					chessmanY = (boardY - 35) / 30;
					//System.out.println("x = " + chessmanX + "; y = " + chessmanY);
					if (this.board[chessmanX][chessmanY] == 2) {
						this.bout++;
						this.board[chessmanX][chessmanY] = 0;
						pcount++;
						if ((ccount == 50) && (pcount == 50)) {
							this.tie = true;
							this.over = true;
						}
						for (int i = 0; i < 672; i++) {
							if (this.ptable[chessmanX][chessmanY][i] && this.win[0][i] != 7) {
								this.win[0][i]++;
							}
							if (this.ctable[chessmanX][chessmanY][i]) {
								this.ctable[chessmanX][chessmanY][i] = false;
								this.win[1][i] = 7;;
							}
						}
						this.player = false;
						this.computer = true;
					} else {
						chessmanX = m1;
						chessmanY = n1;
					}
				}
			}
		}
	}

	// override paint method
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < 16; i++) {
			g.drawLine(50, 50 + i * 30, 500, 50 + i * 30);
		}  // draw 16 horizontal lines
		for (int i = 0; i < 16; i++) {
			g.drawLine(50 + i * 30, 50, 50 + i * 30, 500);
		}  // draw 16 vertical lines
		for (int i = 0; i < 16; i++) {
			String number = Integer.toString(i);
			g.drawString(number, 46 + 30 * i, 45); // draw horizontal indexs.
		}
		for (int i = 1; i < 16; i++) {
			String number = Integer.toString(i);
			g.drawString(number, 33, 53 + i * 30); // draw vertical indexs.
		}
		updatePaint(g);
	}

	// everytime click mouse to place a chessman, invoke this method.
	public void updatePaint(Graphics g) {
		//System.out.println("I'm updatePaint");
		if (!this.over) {
			if (this.computer) {
				this.computerTurn();
			}
			for (int i = 0; i <= 1; i++) {
				for (int j = 0; j < 672; j++) {
					if (this.win[i][j] == 5) {
						if (i == 0) {  // player wins
							this.pwin = true;
							this.over = true;  // game over
							break;
						} else {
							this.cwin = true; // computer wins
							this.over = true;
							break;
						}
					}
					if (this.over) {
						break;
					}
				}
			}
			// paint all the chessman
			g.setFont(new Font("Serif", Font.PLAIN, 24));
			for (int i = 0; i <= 15; i++) {
				for (int j = 0; j <= 15; j++) {
					if (this.board[i][j] == 0) {
						g.setColor(Color.BLACK);
						g.fillOval(i * 30 + 40, j * 30 + 40, 20, 20);
					}
					if (this.board[i][j] == 1) {
						g.setColor(Color.WHITE);
						g.fillOval(i * 30 + 40, j * 30 + 40, 20, 20);
					}
				}
			}
			g.setColor(Color.RED);
			if (this.pwin) {
				g.drawString("You are great! Play again?", 140, 20);
			}
			if (this.cwin) {
				g.drawString("So sorry, you lost! Try again?", 140, 20);
			}
			if (this.tie) {
				g.drawString("game ended in a tie. Try again?", 140, 20);
			}
		}
	}

	// computer turn to play a piece. Pay attention to this important part.
	public void computerTurn() {
		for (int i = 0; i <= 15; i++) {
			for( int j = 0; j <= 15; j++) {
				this.pgrades[i][j] = 0;  // clear black side grade
				if (this.board[i][j] == 2) {   // search in none chessman area.
					for (int k = 0; k < 672; k++) { 
						if (this.ptable[i][j][k]) {
							switch (this.win[0][k]) {
								case 1: // one chessman in a line
									this.pgrades[i][j] += 5;
									break;
								case 2: // two chessnam in a line
									this.pgrades[i][j] += 50;
									break;
								case 3: // three chessman in a line
									this.pgrades[i][j] += 180;
									break;
								case 4: // three chessman in a line
									this.pgrades[i][j] += 400;
									break;
							}
						}
					}
				}
				this.cgrades[i][j] = 0;  // clear white side grade
				if (this.board[i][j] == 2) {
					for (int k = 0; k < 672; k++) {
						if (this.ctable[i][j][k]) {
							switch (this.win[1][k]) {
								case 1:
									this.cgrades[i][j] += 5;
									break;
								case 2: 
									this.cgrades[i][j] += 52;
									break;
								case 3:
									this.cgrades[i][j] += 100;
									break;
								case 4:
									this.cgrades[i][j] += 400;
									break;
							}
						}
					}
				}
			}
		}
		if (this.start) {
			if (this.board[4][4] == 2) {
				chessmanX = 4;
				chessmanY = 4;
			} else {
				chessmanX = 5;
				chessmanY = 5;
			}
			this.start = false;
		} else {
			for (int i = 0; i < 16; i++) {
				for (int j = 0; j < 16; j++) {
					if (this.board[i][j] == 2) {
						if (this.cgrades[i][j] >= this.cgrade) {
							this.cgrade = this.cgrades[i][j];
							this.mat = i;
							this.nat = j;
						}
						if (this.pgrades[i][j] >= this.pgrade) {
							this.pgrade = this.pgrades[i][j];
							this.mde = i;
							this.nde = j;
						}
					}
				}
			}
			if (this.cgrade >= this.pgrade) {
				chessmanX = mat;
				chessmanY = nat;
			} else {
				chessmanX = mde;
				chessmanY = nde;
			}
		}
		this.cgrade = 0;
		this.pgrade = 0;
		this.board[chessmanX][chessmanY] = 1;
		ccount++;
		if ((ccount == 50) && (pcount == 50)) {
			this.tie = true;
			this.over = true;
		}
		for (int i = 0; i < 672; i++) {
			if (this.ctable[chessmanX][chessmanY][i] && this.win[1][i] != 7) {
				this.win[1][i]++;
			}
			if (this.ptable[chessmanX][chessmanY][i]) {
				this.ptable[chessmanX][chessmanY][i] = false;
				this.win[0][i] = 7;
			}
		}
		this.player = true;
		this.computer = false;
	}

	// game init, value all variables.
	public void ResetGame() {
		//System.out.println("you've reset the game.");
		// init the chess panel.
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				this.pgrades[i][j] = 0;
				this.cgrades[i][j] = 0;
				this.board[i][j] = 2;
			}
		}
		// check for all the posible rights of five-piece-line
		// horizontal
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 12; j++) {
				for (int k = 0; k < 5; k++) {
					this.ptable[j + k][i][icount] = true;
					this.ctable[j + k][i][icount] = true;
				}
				icount++;
			}
		}
		// vertical
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 12; j++) {
				for (int k = 0; k < 5; k++) {
					this.ptable[i][j + k][icount] = true;
					this.ctable[i][j + k][icount] = true;
				}
				icount++;
			}
		}
		// right oblique
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				for (int k = 0; k < 5; k++) {
					this.ptable[j + k][i + k][icount] = true;
					this.ctable[j + k][i + k][icount] = true;
				}
				icount++;
			}
		}
		// left oblique
		for (int i = 0; i < 12; i++) {
			for (int j = 15; j >= 4; j--) {
				for (int k = 0; k < 5; k++) {
					this.ptable[j - k][i + k][icount] = true;
					this.ctable[j - k][i + k][icount] = true;
				}
				icount++;
			}
		}
		// init number of chessman in one line
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 672; j++) {
				this.win[i][j] = 0;
			}
		}
		this.player = true;
		this.icount = 0;
		this.ccount = 0;
		this.pcount = 0;
		this.start = true;
		this.over = false;
		this.pwin = false;
		this.cwin = false;
		this.tie = false;
		this.bout = 1;  
	}
}


