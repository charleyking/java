package club.charleyking.panel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import club.charleyking.listener.ToolBarListener;
import club.charleyking.util.CenterPanel;
import club.charleyking.util.GUIUtil;

public class MainPanel extends JPanel {
	static {
		GUIUtil.useLNF();
	}
	
	public static MainPanel instance = new MainPanel();
	public JToolBar jtb = new JToolBar();
	public JButton b_spend = new JButton();
	public JButton b_record = new JButton();
	public JButton b_category = new JButton();
	public JButton b_report = new JButton();
	public JButton b_config = new JButton();
	public JButton b_backup = new JButton();
	public JButton b_restore = new JButton();
	
	public CenterPanel workingPanel;
	
	private MainPanel() {
		GUIUtil.setImgIcon(b_spend, "home.png", "spend");
		GUIUtil.setImgIcon(b_record, "record.png", "record");
		GUIUtil.setImgIcon(b_category, "category2.png", "category");
		GUIUtil.setImgIcon(b_report, "report.png", "report");
		GUIUtil.setImgIcon(b_config, "config.png", "config");
		GUIUtil.setImgIcon(b_backup, "backup.png", "backup");
		GUIUtil.setImgIcon(b_restore, "restore.png", "restore");
		jtb.add(b_spend);
		jtb.add(b_record);
		jtb.add(b_category);
		jtb.add(b_report);
		jtb.add(b_config);
		jtb.add(b_backup);
		jtb.add(b_restore);
		jtb.setFloatable(false);
		
		workingPanel = new CenterPanel(0.8);
		setLayout(new BorderLayout());
		add(jtb, BorderLayout.NORTH);
		add(workingPanel, BorderLayout.CENTER);
		
		addListener();
	}
	
	private void addListener() {
		ToolBarListener listener = new ToolBarListener();
		b_spend.addActionListener(listener);
		b_record.addActionListener(listener);
		b_category.addActionListener(listener);
		b_report.addActionListener(listener);
		b_config.addActionListener(listener);
		b_backup.addActionListener(listener);
		b_restore.addActionListener(listener);
	}
	
	public static void main(String[] args) {
		GUIUtil.showPanel(MainPanel.instance, 1);
	}
}
