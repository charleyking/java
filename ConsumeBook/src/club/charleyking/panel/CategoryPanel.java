package club.charleyking.panel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import club.charleyking.entity.Category;
import club.charleyking.listener.CategoryListener;
import club.charleyking.model.CategoryTableModel;
import club.charleyking.service.CategoryService;
import club.charleyking.util.ColorUtil;
import club.charleyking.util.GUIUtil;

public class CategoryPanel extends WorkingPanel {
	static {
		GUIUtil.useLNF();
	}
	
	public static CategoryPanel instance = new CategoryPanel();
	public JButton b_add = new JButton("add");
	public JButton b_edit = new JButton("edit");
	public JButton b_delete = new JButton("delete");
	public String[] columnNames = new String[] {"category", "times"};
	
	public CategoryTableModel ctm = new CategoryTableModel();
	public JTable jt = new JTable(ctm);
	
	public CategoryPanel() {
		GUIUtil.setColor(ColorUtil.blueColor, b_add, b_delete, b_edit);
		JScrollPane jsp = new JScrollPane(jt);
		JPanel p_submit = new JPanel();
		p_submit.add(b_add);
		p_submit.add(b_delete);
		p_submit.add(b_edit);
		
		this.setLayout(new BorderLayout());
		this.add(jsp, BorderLayout.CENTER);
		this.add(p_submit,BorderLayout.SOUTH);
		
		addListener();
	}
	
	public Category getSelectedCategory() {
		int index = jt.getSelectedRow();
		return ctm.cs.get(index);
	}
	
	public void updateData() {
		ctm.cs = new CategoryService().list();
		jt.updateUI();
		jt.getSelectionModel().setSelectionInterval(0, 0);
		if (0 == ctm.cs.size()) {
			b_edit.setEnabled(false);
			b_delete.setEnabled(false);
		} else {
			b_edit.setEnabled(true);
			b_delete.setEnabled(true);
		}
	}
	
	public void addListener() {
		CategoryListener listener = new CategoryListener();
		b_add.addActionListener(listener);
		b_edit.addActionListener(listener);
		b_delete.addActionListener(listener);
	}
	
	public static void main(String[] args) {
		GUIUtil.showPanel(CategoryPanel.instance);
	}
}
