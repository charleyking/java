package club.charleyking.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import club.charleyking.entity.Category;
import club.charleyking.panel.CategoryPanel;
import club.charleyking.service.CategoryService;

public class CategoryListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		CategoryPanel cp = CategoryPanel.instance;
		JButton b = (JButton) e.getSource();
		if (b == cp.b_add) {
			String name = JOptionPane.showInputDialog(null);
			if (0 == name.length()) {
				JOptionPane.showMessageDialog(cp, "can not be null");
				return;
			}
			new CategoryService().add(name);
		}
		
		if (b == cp.b_edit) {
			Category c = cp.getSelectedCategory();
			int id = c.id;
			String name = JOptionPane.showInputDialog("edit name", c.name);
			if (0 == name.length()) {
				JOptionPane.showMessageDialog(cp, "can not be null");
				return;
			}
		}
		
		if (b == cp.b_delete) {
			Category c = cp.getSelectedCategory();
			if (0 != c.recordNumber) {
				JOptionPane.showMessageDialog(cp, "can not delete this");
				return;
			}
			if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(cp, "confirm delete")) {
				return;
			} else {
				int id = c.id;
				new CategoryService().delete(id);
			}
		}
		cp.updateData();
	}
}
