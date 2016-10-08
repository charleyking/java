package club.charleyking.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import club.charleyking.entity.Category;
import club.charleyking.panel.CategoryPanel;
import club.charleyking.panel.MainPanel;
import club.charleyking.panel.RecordPanel;
import club.charleyking.service.RecordService;
import club.charleyking.util.GUIUtil;

public class RecordListener implements ActionListener {
	@Override 
	public void actionPerformed(ActionEvent e) {
		RecordPanel rp = RecordPanel.instance;
		if (0 == rp.cbModel.cs.size()) {
			JOptionPane.showMessageDialog(rp, "please add category first");
			MainPanel.instance.workingPanel.show(CategoryPanel.instance);
			return;
		}
		
		if (!GUIUtil.checkZero(rp.tfSpend, "spend")) {
			return;
		}
		
		int spend = Integer.parseInt(rp.tfSpend.getText());
		Category c = rp.getSelectedCategory();
		String comment = rp.tfComment.getText();
		Date date = rp.datepick.getDate();
		new RecordService().add(spend, c, comment, date);
		JOptionPane.showMessageDialog(rp, "add successfully");
	}
}
