package club.charleyking.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class BorderDemo extends JPanel{
	Border blackline, raisedetched, loweredetched, empty;
	Border paneEdge;
	JPanel simpleBorderPanel;
	
	public BorderDemo() {
		super(new GridLayout(1,0));
		paneEdge = BorderFactory.createEmptyBorder(0,10,10,10);
		blackline = BorderFactory.createLineBorder(Color.BLACK);
		raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		empty = BorderFactory.createEmptyBorder();
		simpleBorderPanel = new JPanel();
		simpleBorderPanel.setBorder(paneEdge);
		simpleBorderPanel.setLayout(new BoxLayout(simpleBorderPanel, BoxLayout.Y_AXIS));
		addBorderForPanel(blackline, "line border", simpleBorderPanel);
	}
	
	public void addBorderForPanel(Border border, String description, JPanel panel) {
		JPanel comp = new JPanel(new GridLayout(1,1), false);
		JLabel label= new JLabel(description, JLabel.CENTER);
		comp.add(label);
		comp.setBorder(border);
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.add(comp);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("he");
		BorderDemo demo = new BorderDemo();
		frame.add(demo);
		//frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
}
