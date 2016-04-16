import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class test extends JPanel {
	public test() {
		
	}
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.blue);
		g2.fillRect(10, 10, 50, 50);
	}

	public static void main(String[] args) {
		JFrame a = new JFrame();
		a.setName("charleyking's clock");
		a.setVisible(true);
		a.setFont(new Font("楷体",Font.BOLD,24));
		a.setSize(150, 150);
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a.getContentPane().add(new test());
	}
}
