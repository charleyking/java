import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOperationPane;


public class FiveChessFrame extends JFrame implements MouseListener, Runnable {
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	BufferedImage bgImage = null;
	int x = 0;
	int y = 0;
	// save all chess's scale, no chess is 0, black is 1, white is 2
	int[][] allChess = new int[19][19];
	// flag next step is black side or white side
	boolean isBlack = true;
	// flag if the game can go 
	boolean canPlay = true;
	// save show message
	String message = "black go first";
	// save max time
	int maxTime = 0;
	// the thread to back-count time
	Thread t = new Thread(this);
	// save the remainded time for black and white side
	int blackTime = 0;
	int whiteTime = 0;
	// save the remainded time message
	String blackMessage = "no limit";
	String whiteMessage = "no limit";


	// the constructure function
	public FiveChessFrame() {
		this.setTitle("Wuziqi");
		this.setSize(500, 500);
		this.setLocation((width-500)/2, (height-500)/2);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//add frame listener
		this.addMouseListener(this);
		this.setVisible(true);
		t.start;
		t.suspend();
		// refresh screen, in void of can't display when start game
		this.repaint();
		String imagePath = "";
		try {
			imagePath = System.getProperty("user.dir") + "/bin/image/background.jpg";
			bgImage = ImageIO.read(new File(imagePath.replaceAll("\\\\", "/")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	// paint method
	public void paint(Graphics g) {
		// double buffer in void of screen bleaz
		BufferedImage bi = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
		Graphics g2 = bi.createGraphics();
		g2.setColor(Color.BLACK);
		// paint background
		g2.drawImage(bgImage, 1, 20, this);
		
	}
}
