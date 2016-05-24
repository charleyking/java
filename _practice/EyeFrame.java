import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class EyeFrame extends JFrame {
	int angle = 300;
	int amplitude = 30;
	int ampl = 0;
	boolean flag = true;
	int centerX = 1024/2;
	int centerY = 768/2;
	Color color = Color.red;
	int blackBallSemi = 25;

	public EyeFrame() {
		this.setTitle("Eyes");
		this.setSize(centerX*2, centerY*2);
		this.getContentPane().setBackground(Color.black);
		startRun();
		this.setVisible(true);
	}

	public void paint(Graphics graphics) {
		super.paint(graphics);
		graphics.setColor(Color.white);
		graphics.fillRect(centerX-angle/2, centerY-amplitude, angle, amplitude*2);
		graphics.setColor(Color.darkGray);
		graphics.fillOval(centerX-blackBallSemi, centerY-blackBallSemi, blackBallSemi*2, blackBallSemi*2);
		graphics.setColor(Color.white);
		graphics.fillOval(centerX-blackBallSemi/2/2, centerY-blackBallSemi/2/2, blackBallSemi*2, blackBallSemi*2);
		graphics.setColor(color);
		for(int i=0; i<angle; i++) {
			graphics.drawLine(centerX-angle/2+i, centerY-30, centerX-angle/2+i, centerY-(int)(Math.sin(Math.PI*i/angle)*ampl));
			graphics.drawLine(centerX-angle/2+i, centerY+30, centerX-angle/2+i, centerY+(int)(Math.sin(Math.PI*i/angle)*ampl));
		}
	}

	public void startRun() {
			new Thread() {
				public void run() {
					while(true) {
						if(flag) {
							ampl++;
							if(ampl>=amplitude) {
								flag = false;
							}
						} else {
							ampl--;
							if(ampl<=0) {
								flag = true;
							}
						} try {
							Thread.sleep(40);
						} catch(InterruptedException e) {
							e.printStackTrace();
						}
						repaint();
					}
				}
			}.start();
	}
		
	public static void main(String args[]) {
		new EyeFrame();
	}
}