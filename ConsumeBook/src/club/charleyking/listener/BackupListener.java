package club.charleyking.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import club.charleyking.panel.BackupPanel;
import club.charleyking.panel.ConfigPanel;
import club.charleyking.panel.MainPanel;
import club.charleyking.service.ConfigService;
import club.charleyking.util.MysqlUtil;

public class BackupListener implements ActionListener {
	@Override 
	public void actionPerformed(ActionEvent e) {
		BackupPanel bp = BackupPanel.instance;
		String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
		if (0 == mysqlPath.length()) {
			JOptionPane.showMessageDialog(bp, "config mysql path at first");
			MainPanel.instance.workingPanel.show(ConfigPanel.instance);
			ConfigPanel.instance.tfMysqlPath.grabFocus();
			return;
		}
		
		JFileChooser fc = new JFileChooser();
		fc.setSelectedFile(new File("bill.sql"));
		fc.setFileFilter(new FileFilter() {
			@Override 
			public String getDescription() {
				return ".sql";
			}
			@Override
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".sql");
			}
		});
		
		int returnVal = fc.showSaveDialog(bp);
		File file = fc.getSelectedFile();
		System.out.println(file);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println(file);
			if (!file.getName().toLowerCase().endsWith(".sql")) {
				file = new File(file.getParent(),file.getName() + ".sql");
			}
			System.out.println(file);
			try {
				MysqlUtil.backup(mysqlPath, file.getAbsolutePath());
				JOptionPane.showMessageDialog(bp, "backup successful" + file.getAbsolutePath());
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(bp, "backup failure");
			}
		}
	}
}
