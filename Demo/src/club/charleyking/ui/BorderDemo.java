/**
 * A demo that shows how swing Border works.
 */

package club.charleyking.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class BorderDemo extends JPanel{
	Border blackline, raisedetched, loweredetched, raisedbevel, loweredbevel, empty;
	Border paneEdge;
	TitledBorder titledBorderDefault, titledBorderBlack, titledBorderEmpty;
	JPanel simpleBorderPanel, titledBorderPanel;
	
	public BorderDemo() {
		super(new GridLayout(1,0));
		//create border types
		paneEdge = BorderFactory.createEmptyBorder(0,10,10,10);
		blackline = BorderFactory.createLineBorder(Color.BLACK);
		raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		raisedbevel = BorderFactory.createRaisedBevelBorder();
		loweredbevel = BorderFactory.createLoweredBevelBorder();
		empty = BorderFactory.createEmptyBorder();
		titledBorderDefault = BorderFactory.createTitledBorder("title");
		titledBorderBlack = BorderFactory.createTitledBorder(blackline, "title");
		titledBorderEmpty = BorderFactory.createTitledBorder(empty, "title empty");
		//new and setup simple border panel
		simpleBorderPanel = new JPanel();
		simpleBorderPanel.setBorder(paneEdge);
		simpleBorderPanel.setLayout(new BoxLayout(simpleBorderPanel, BoxLayout.Y_AXIS));
		//add components in simple border tab panel
		addBorderForPanel(blackline, "line border", simpleBorderPanel);
		addBorderForPanel(raisedetched, "raised etched border", simpleBorderPanel);
		addBorderForPanel(loweredetched, "lowered etched border", simpleBorderPanel);
		addBorderForPanel(raisedbevel, "raised bevel border", simpleBorderPanel);
		addBorderForPanel(loweredbevel, "lowered bevel border", simpleBorderPanel);
		addBorderForPanel(empty, "empty border", simpleBorderPanel);
		//new add setup title border panel
		titledBorderPanel = new JPanel();
		titledBorderPanel.setBorder(paneEdge);
		titledBorderPanel.setLayout(new BoxLayout(titledBorderPanel, BoxLayout.Y_AXIS));
		//add components in title border tab panel
		addBorderForPanel(titledBorderDefault, "default title border", titledBorderPanel);
		addBorderForPanel(titledBorderBlack, "blackline title border", titledBorderPanel);
		addBorderForPanel(titledBorderEmpty, "empty title border", titledBorderPanel);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("simple", simpleBorderPanel);
		tabbedPane.addTab("title", titledBorderPanel);
		this.add(tabbedPane);
	}
	
	public void addBorderForPanel(Border border, String description, JPanel panel) {
		JPanel compPanel = new JPanel(new GridLayout(1,1), false);
		JLabel label= new JLabel(description, JLabel.CENTER);
		compPanel.add(label);
		compPanel.setBorder(border);
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.add(compPanel);
	}
	
	public void createAndShowGUI(BorderDemo borderDemo) {
		JFrame frame = new JFrame("hek");
		frame.add(borderDemo);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		BorderDemo borderDemo = new BorderDemo();
		borderDemo.createAndShowGUI(borderDemo);
	}
}
