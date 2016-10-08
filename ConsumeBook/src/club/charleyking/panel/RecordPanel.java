package club.charleyking.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import club.charleyking.entity.Category;
import club.charleyking.listener.RecordListener;
import club.charleyking.model.CategoryComboBoxModel;
import club.charleyking.service.CategoryService;
import club.charleyking.util.ColorUtil;
import club.charleyking.util.GUIUtil;

public class RecordPanel extends WorkingPanel {
	static {
		GUIUtil.useLNF();
	}
	public static RecordPanel instance = new RecordPanel();
	JLabel lSpend = new JLabel("spend");
	JLabel lCategory = new JLabel("category");
	JLabel lComment = new JLabel("comment");
	JLabel lDate = new JLabel("date");
	public JTextField tfSpend = new JTextField("0");
	public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
	public JComboBox<String> cbCategory = new JComboBox<>(cbModel);
	public JTextField tfComment = new JTextField();
	public JXDatePicker datepick = new JXDatePicker(new Date());
	
	JButton b_submit = new JButton("add one");
	
	public RecordPanel() {
		GUIUtil.setColor(ColorUtil.grayColor, lSpend, lCategory, lComment, lDate);
		GUIUtil.setColor(ColorUtil.blueColor, b_submit);
		JPanel pInput = new JPanel();
		JPanel pSubmit = new JPanel();
		int gap = 30;
		pInput.setLayout(new GridLayout(4, 2, gap, gap));
		pInput.add(lSpend);
		pInput.add(tfSpend);
		pInput.add(lCategory);
		pInput.add(cbCategory);
		pInput.add(lComment);
		pInput.add(tfComment);
		pInput.add(lDate);
		pInput.add(datepick);
		pSubmit.add(b_submit);
		
		this.setLayout(new BorderLayout());
		this.add(pInput, BorderLayout.NORTH);
		this.add(pSubmit, BorderLayout.CENTER);
		
		addListener();
	}
	
	public Category getSelectedCategory() {
		return (Category) cbCategory.getSelectedItem();
	}
	
	public void resetInput() {
		tfSpend.setText("0");
		tfComment.setText("");
		if (0!= cbModel.cs.size()) {
			//cbCategory.setSelectedIndex(0);
		}
		datepick.setDate(new Date());
	}
	
	@Override 
	public void addListener() {
		RecordListener listener = new RecordListener();
		b_submit.addActionListener(listener);
	}
	
	@Override 
	public void updateData() {
		cbModel.cs = new CategoryService().list();
		cbCategory.updateUI();
		resetInput();
		tfSpend.grabFocus();
	}
	
	public static void main(String[] args) {
		GUIUtil.showPanel(RecordPanel.instance);
	}
	
}