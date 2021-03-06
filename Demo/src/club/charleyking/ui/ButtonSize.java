/**
 * Adjust button size on a panel.
 */

package club.charleyking.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ButtonSize extends JFrame {
	JPanel panel = new JPanel();
	JButton[] buttons = new JButton[10];
	JTextField textFiled = new JTextField();
	JPanel panelGrid = new JPanel();
	Border panelEdge = BorderFactory.createEmptyBorder(40, 10, 10, 10);
	
	public ButtonSize() {
		//panel.setLayout(new BorderLayout());
		//button.setPreferredSize(new Dimension(10,10));
		//panel.add(button,BorderLayout.NORTH);
		/*for (int i=0; i<10; i++) {
			buttons[i] = new JButton(String.valueOf(i));
			buttons[i].setPreferredSize(new Dimension(100,40));
			panel.add(buttons[i]);
		}*/
		panelGrid.setBorder(panelEdge);
		for (int j=0; j<10; j++) {
			buttons[j] = new JButton(String.valueOf(j));
			//buttons[j].setPreferredSize(new Dimension(20,20));
			panelGrid.setLayout(new GridLayout(4,4,40,40));
			panelGrid.add(buttons[j]);
		}
		this.getContentPane().add(panelGrid);
		init();
	}
	
	public void init() {
		this.setTitle("button size resize");
		this.setSize(400,400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		ButtonSize buttonSize = new ButtonSize();
	}
}
