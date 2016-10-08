package club.charleyking.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import club.charleyking.listener.ConfigListener;
import club.charleyking.service.ConfigService;
import club.charleyking.util.ColorUtil;
import club.charleyking.util.GUIUtil;

public class ConfigPanel extends WorkingPanel {
	static {
		GUIUtil.useLNF();
	}
	public static ConfigPanel instance = new ConfigPanel();
	JLabel lBudget = new JLabel("monthly budget");
	public JTextField tfBudget = new JTextField("0");
	JLabel lMysql = new JLabel("mysql install path");
	public JTextField tfMysqlPath = new JTextField("");
	JButton b_submit = new JButton("submit");
	
	public ConfigPanel() {
		GUIUtil.setColor(ColorUtil.grayColor, lBudget, lMysql);
		GUIUtil.setColor(ColorUtil.blueColor, b_submit);
		JPanel pInput = new JPanel();
		JPanel pSubmit = new JPanel();
		int gap = 40;
		pInput.setLayout(new GridLayout(4, 1, gap, gap));
		pInput.add(lBudget);
		pInput.add(tfBudget);
		pInput.add(lMysql);
		pInput.add(tfMysqlPath);
		
		this.setLayout(new BorderLayout());
		this.add(pInput, BorderLayout.NORTH);
		pSubmit.add(b_submit);
		this.add(pSubmit, BorderLayout.CENTER);
		
		addListener();
	}
	
	public void addListener() {
		ConfigListener cl = new ConfigListener();
		b_submit.addActionListener(cl);
	}
	
	public void updateData() {
		String budget = new ConfigService().get(ConfigService.budget);
		String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
		tfBudget.setText(budget);
		tfMysqlPath.setText(mysqlPath);
		tfBudget.grabFocus();
	}
	
	public static void main(String[] args) {
		GUIUtil.showPanel(ConfigPanel.instance);
	}
}
