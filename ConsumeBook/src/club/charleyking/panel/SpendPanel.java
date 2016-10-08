package club.charleyking.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import club.charleyking.page.SpendPage;
import club.charleyking.service.SpendService;
import club.charleyking.util.CircleProgressBar;
import club.charleyking.util.ColorUtil;
import club.charleyking.util.GUIUtil;
 
public class SpendPanel extends WorkingPanel {
	static {
		GUIUtil.useLNF();
	}
	public static SpendPanel instance = new SpendPanel();
	
    public JLabel lMonthSpend = new JLabel("month spend");
    public JLabel lTodaySpend = new JLabel("today spend");
    public JLabel lAvgSpendPerDay = new JLabel("spend per day");
    public JLabel lMonthLeft = new JLabel("month left");
    public JLabel lDayAvgAvailable = new JLabel("left per day");
    public JLabel lMonthLeftDay = new JLabel("left day");
 
    public JLabel vMonthSpend = new JLabel("￥2300");
    public JLabel vTodaySpend = new JLabel("￥25");
    public JLabel vAvgSpendPerDay = new JLabel("￥120");
    public JLabel vMonthAvailable = new JLabel("￥2084");
    public JLabel vDayAvgAvailable = new JLabel("￥389");
    public JLabel vMonthLeftDay = new JLabel("15天");
    
    CircleProgressBar bar;
    
    public SpendPanel() {
    	this.setLayout(new BorderLayout());
    	bar = new CircleProgressBar();
    	bar.setBackgroundColor(ColorUtil.blueColor);
    	GUIUtil.setColor(ColorUtil.grayColor, lMonthSpend, lTodaySpend, lAvgSpendPerDay, lMonthLeft, lDayAvgAvailable,
    			lMonthLeftDay, vAvgSpendPerDay, vMonthAvailable, vDayAvgAvailable, vMonthLeftDay);
    	GUIUtil.setColor(ColorUtil.blueColor, vMonthSpend, vTodaySpend);
    	this.add(center(), BorderLayout.CENTER);
    	this.add(south(), BorderLayout.SOUTH);
    }
    
    private JPanel center() {
    	JPanel jp = new JPanel();
    	jp.setLayout(new BorderLayout());
    	jp.add(west(), BorderLayout.WEST);
    	jp.add(center2(), BorderLayout.CENTER);
    	return jp;
    }
    
    private Component center2() {
    	return bar;
    }
    
    private Component west() {
    	JPanel jp = new JPanel();
    	jp.setLayout(new GridLayout(4,1));
    	jp.add(lMonthSpend);
    	jp.add(vMonthSpend);
    	jp.add(lTodaySpend);
    	jp.add(vTodaySpend);
    	return jp;
    }
    
    private JPanel south() {
    	JPanel jp = new JPanel();
    	jp.setLayout(new GridLayout(2, 4));
    	jp.add(lAvgSpendPerDay);
    	jp.add(lMonthLeft);
    	jp.add(lDayAvgAvailable);
    	jp.add(lMonthLeftDay);
    	jp.add(vAvgSpendPerDay);
    	jp.add(vMonthAvailable);
    	jp.add(vDayAvgAvailable);
    	jp.add(vMonthLeftDay);
    	return jp;
    }
    
    @Override 
    public void addListener() {
    	
    }
    
    @Override 
    public void updateData() {
    	SpendPage spendPage = new SpendService().getSpendPage();
    	vMonthSpend.setText(spendPage.monthSpend);
    	vTodaySpend.setText(spendPage.todaySpend);
    	vAvgSpendPerDay.setText(spendPage.avgSpendPerDay);
    	vMonthAvailable.setText(spendPage.monthAvailable);
    	vDayAvgAvailable.setText(spendPage.dayAvgAvailable);
    	vMonthLeftDay.setText(spendPage.monthLeftDay);
    	
    	bar.setProgress(spendPage.usagePercentage);
    	
    	if (spendPage.isOverSpend) {
    		vMonthAvailable.setForeground(ColorUtil.warningColor);
    		vMonthSpend.setForeground(ColorUtil.warningColor);
    		vTodaySpend.setForeground(ColorUtil.warningColor);
    	} else {
    		vMonthAvailable.setForeground(ColorUtil.grayColor);
    		vMonthSpend.setForeground(ColorUtil.blueColor);
    		vTodaySpend.setForeground(ColorUtil.blueColor);
    	}
    	bar.setForegroundColor(ColorUtil.getByPercentage(spendPage.usagePercentage));
    	addListener();
    }
    
    public static void main(String[] args) {
    	GUIUtil.showPanel(SpendPanel.instance);
    }
     
}