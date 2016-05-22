import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ChessBoard extends JPanel implements MouseListener {
	public static final int MARGIN = 30;
	public static final int GRID_SPAN = 35;
	public static final int ROWS = 10;
	public static final int COLS = 10;
	Point[] chessList = new Point[(ROWS + 1) * (COLS + 1)];
	boolean isBlack = true;
	boolean gameOver = false;
	int chessCount;
	int xIndex, yIndex;

	public ChessBoard() {
		setBackground(Color.ORANGE);
		addMouseListener(this);
		addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
			}
			public void mouseMoved(MouseEvent e) {
				int x1 = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
				int y1 = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
				if (x1 < 0 || x1 > ROWS || y1 < 0 || y1 > COLS || gameOver || findChess(x1, y1)) {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} else {
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			}
		});
	}

	public 
	public void restartGame() {

	}

	public void goBack() {

	}

}

class Point {
	private int x;
	private int y;
	private Color color;
	public static final int DIAMETER = 30;
	public Point (int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Color getColor() {
		return color;
	}
}