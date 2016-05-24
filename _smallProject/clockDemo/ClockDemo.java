/*This is a clock demo, based on some source code files downloaded on web*/

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
public class ClockDemo extends JFrame implements Runnable {
	final int Xpoint = 180;
	final int Ypoint = 180;
	final int R = 100;
	Thread clock;
	//开始写构造函数
	public ClockDemo() {
		setTitle("Charleyking's clock");
		setSize(360,360);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFont(new Font("楷体",Font.BOLD,24));
		start();
	}
	
	public void start() {
		if(clock == null) {
		clock = new Thread(this);
		clock.start();
		}
	}
	
	//重写run方法
	public void run() {
		while(clock != null) {
			repaint();
			try {
				Thread.sleep(1000);
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void sleep() {
		clock = null;
	}

	//开始写paint方法
	public void paint(Graphics g) {
		Graphics2D gg = (Graphics2D)g;
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
		Calendar now = new GregorianCalendar();
		now.setTime(new Date());
		//填充背景色和底板黑色（否则会略显透明）
		gg.setColor(Color.PINK);
		Dimension dim = getSize();
		gg.fillRect(0,0,dim.width,dim.height);
		//绘制上方日期
		g.setColor(Color.BLACK);
		g.drawString(dateFormat.format(now.getTime()),50,60);
		//绘制时钟
		int hour=now.get(Calendar.HOUR_OF_DAY);
   		int minute=now.get(Calendar.MINUTE);   
   		int second=now.get(Calendar.SECOND);
   		//设置时钟字体
   		gg.setFont(new Font("SAN_SERIF",Font.BOLD,15));
   		//开始360度for循环
   		for(int i=0,num=0;i<360;i+=6) { 
			double alfa = Math.toRadians(i);
			//获取端点坐标 
			int xPos=Xpoint+(int)(R*Math.sin(alfa));
			int yPos=Ypoint-(int)(R*Math.cos(alfa));
			//绘制时钟的四个数字
			if(i%90==0) {
				if(num==0) {
					num=12;
					gg.setColor(Color.RED);
					gg.drawString(""+num, xPos-5,yPos+3);
					num=3;
				} else if(num==3) {
					gg.setColor(Color.RED);
					gg.drawString(""+num, xPos-5,yPos+3);
					num=6;
				} else if(num==6) {
					gg.setColor(Color.RED);
					gg.drawString(""+num, xPos-5,yPos+3);
					num=9;
				} else {
					gg.setColor(Color.RED);
					gg.drawString(""+num, xPos-5,yPos+3);
				}
			} else { 
			  	gg.setColor(Color.BLACK);
			  	gg.drawString(".",xPos,yPos);
			}
		}
		//绘制秒针
		int xSecond=(int)(Xpoint+(R-10)*Math.sin(second*(2*Math.PI/60)));
    	int ySecond=(int)(Ypoint-(R-10)*Math.cos(second*(2*Math.PI/60)));
    	gg.setColor(Color.RED);
    	gg.drawLine(Xpoint,Ypoint,xSecond,ySecond);
    	//绘制分针
    	int xMinute=(int)(Xpoint+(R-35)*Math.sin((minute+second/60)*(2*Math.PI/60)));
    	int yMinute=(int)(Ypoint-(R-35)*Math.cos((minute+second/60)*(2*Math.PI/60)));
    	gg.setColor(Color.YELLOW);
    	gg.drawLine(Xpoint,Ypoint,xMinute,yMinute);
    	//绘制时针
    	int xHour=(int)(Xpoint+(R-50)*Math.sin((hour+minute/60+second/60/60)*(2*Math.PI/12)));
    	int yHour=(int)(Ypoint-(R-50)*Math.cos((hour+minute/60+second/60/60)*(2*Math.PI/12)));
    	gg.setColor(Color.BLUE);
    	gg.drawLine(Xpoint,Ypoint,xHour,yHour);
    	//绘制下方日期
    	String time = "";
    	if(hour<=9) {
    		time += "0" + hour + ":";
    	} else {
    		time += hour + ":";
    	}
    	if(minute<=9) {
    		time += "0" + minute + ":";
    	} else {
    		time += minute + ":";
    	}
    	if(second<=9) {
    		time += "0" + second;
    	} else {
    		time += second;
    	}
    	gg.setColor(Color.WHITE);
    	gg.setFont(new Font("SAN_SERIF",Font.BOLD,24));
    	gg.drawString(time,130,340);
	}

	public static void main(String args[]) {
		new ClockDemo();
	}
}
