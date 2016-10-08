package club.charleyking.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import club.charleyking.panel.BackupPanel;
import club.charleyking.panel.CategoryPanel;
import club.charleyking.panel.ConfigPanel;
import club.charleyking.panel.MainPanel;
import club.charleyking.panel.RecordPanel;
import club.charleyking.panel.ReportPanel;
import club.charleyking.panel.RestorePanel;
import club.charleyking.panel.SpendPanel;

public class ToolBarListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		MainPanel mp = MainPanel.instance;
		JButton jb = (JButton) e.getSource();
		if (jb == mp.b_spend) {
			mp.workingPanel.show(SpendPanel.instance);
		}
		if (jb == mp.b_record) {
			mp.workingPanel.show(RecordPanel.instance);
		}
		if (jb == mp.b_category) {
			mp.workingPanel.show(CategoryPanel.instance);
		}
		if (jb == mp.b_config) {
			mp.workingPanel.show(ConfigPanel.instance);
		}
		if (jb == mp.b_report) {
			mp.workingPanel.show(ReportPanel.instance);
		}
		if (jb == mp.b_backup) {
			mp.workingPanel.show(BackupPanel.instance);
		}
		if (jb == mp.b_restore) {
			mp.workingPanel.show(RestorePanel.instance);
		}
	}

}
