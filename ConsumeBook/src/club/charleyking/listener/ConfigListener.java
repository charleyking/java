package club.charleyking.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import club.charleyking.panel.ConfigPanel;
import club.charleyking.service.ConfigService;
import club.charleyking.util.GUIUtil;

public class ConfigListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		ConfigPanel cp = ConfigPanel.instance;
		if (!GUIUtil.checkNumber(cp.tfBudget, "budget")) {
			return;
		}
		String mysqlPath = cp.tfMysqlPath.getText();
		if (0 != mysqlPath.length()) {
			File commandFile = new File(mysqlPath, "bin/mysql.exe");
			if (! commandFile.exists()) {
				JOptionPane.showMessageDialog(cp, "uncorrect mysql path");
				cp.tfMysqlPath.grabFocus();
				return;
			}
		}
		ConfigService cs = new ConfigService();
		cs.update(ConfigService.budget, cp.tfBudget.getText());
		cs.update(ConfigService.mysqlPath, mysqlPath);
		JOptionPane.showMessageDialog(cp, "change success");
	}
	

}
