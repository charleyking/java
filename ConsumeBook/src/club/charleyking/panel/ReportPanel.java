package club.charleyking.panel;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import club.charleyking.entity.Record;
import club.charleyking.service.ReportService;
import club.charleyking.util.CharUtil;
import club.charleyking.util.GUIUtil;

public class ReportPanel extends WorkingPanel {
	static {
		GUIUtil.useLNF();
	}
	public static ReportPanel instance = new ReportPanel();
	public JLabel jl = new JLabel();
	
	public ReportPanel() {
		this.setLayout(new BorderLayout());
		List<Record> result = new ReportService().listThisMonthRecords();
		Image image = CharUtil.getImage(result, 400, 300);
		ImageIcon icon = new ImageIcon(image);
		jl.setIcon(icon);
		this.add(jl);
		
		addListener();
	}
	
	@Override
	public void addListener() {
		
	}
	
	@Override 
	public void updateData() {
		List<Record> result = new ReportService().listThisMonthRecords();
		Image image = CharUtil.getImage(result, 350, 250);
		ImageIcon icon = new ImageIcon(image);
		jl.setIcon(icon);
	}
	public static void main(String[] args) {
		GUIUtil.showPanel(ReportPanel.instance);
	}
}
