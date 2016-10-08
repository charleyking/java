package club.charleyking.util;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import club.charleyking.panel.WorkingPanel;

public class CenterPanel extends JPanel {
	private double rate;
	private JComponent compo;
	private boolean strench;
	
	public CenterPanel(boolean strench, double rate) {
		this.setLayout(null);
		this.strench = strench;
		this.rate = rate;
	}
	public CenterPanel(double rate) {
		this(true, rate);
	}
	
	public void repaint() {
		if (null != compo) {
			Dimension containerSize = this.getSize();
			Dimension componentSize = compo.getSize();
			if (strench) {
				compo.setSize((int)(containerSize.getWidth()*rate), (int)(containerSize.getHeight()*rate));
			} else {
				compo.setSize(componentSize);
			}
			compo.setLocation(containerSize.width/2-compo.getSize().width/2, containerSize.height/2-compo.getSize().height/2);
		}
		super.repaint();
	}
	
	public void show(JComponent new_compo) {
		this.compo = new_compo;
		Component[] compos = getComponents();
		for (Component compo: compos) {
			remove(compo);
		}
		add(new_compo);
		
		if (new_compo instanceof WorkingPanel) {
			((WorkingPanel) new_compo).updateData();
		}
		this.updateUI();
	}
	
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setSize(200, 200);
		jf.setLocationRelativeTo(null);
		CenterPanel cpanel = new CenterPanel(true, 0.85);
		jf.setContentPane(cpanel);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		JButton jb = new JButton("hello");
		cpanel.show(jb);
	}
}
