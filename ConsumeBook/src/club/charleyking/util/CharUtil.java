package club.charleyking.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.objectplanet.chart.BarChart;
import com.objectplanet.chart.Chart;

import club.charleyking.entity.Record;
import club.charleyking.service.ReportService;

public class CharUtil {
	public static int max(double[] sampleValues) {
		int max = 0;
		for (double v:sampleValues) {
			if (v > max) {
				max = (int) v;
			}
		}
		return max;
	}
	
	private static double[] sampleValues(List<Record> result) {
		double[] sampleValues = new double[result.size()];
		for (int i = 0; i < sampleValues.length; i++) {
			sampleValues[i] = result.get(i).spend;
		}
		return sampleValues;
	}
	
	private static String[] sampleLabels(List<Record> result) {
		String[] sampleLabels = new String[result.size()];
		for (int i = 0; i < sampleLabels.length; i++) {
			if (0 == i % 5) {
				sampleLabels[i] = String.valueOf(i + 1 + "d");
			}
		}
		return sampleLabels;
	}
	
	public static Image getImage(List<Record> result, int width, int height) {
		double[] sampleValues = sampleValues(result);
		String[] sampleLabels = sampleLabels(result);
		int max = max(sampleValues);
		Color[] sampleColors = new Color[] {ColorUtil.blueColor};
		
		BarChart chart = new BarChart();
		chart.setSampleCount(sampleValues.length);
		chart.setSampleValue(0,10, sampleValues[1]);
		chart.setSampleLabels(sampleLabels);
		chart.setSampleColors(sampleColors);
		chart.setRange(0, max * 1.2);
		chart.setValueLinesOn(true);
		chart.setSampleLabelsOn(true);
		chart.setSampleLabelStyle(Chart.BELOW);
		chart.setFont("rangeLabelFont", new Font("Arial", Font.BOLD, 12));
		chart.setLegendOn(true);
		chart.setLegendPosition(Chart.LEFT);
		chart.setLegendLabels(new String[] {"monthly consum chart"});
		chart.setFont("legendFont", new Font("Dialog",Font.BOLD, 13));
		chart.setFont("sampleLabelFont", new Font("Dialog", Font.BOLD, 13));
		chart.setChartBackground(Color.WHITE);
		chart.setBackground(ColorUtil.backgroundColor);
		Image img = chart.getImage(width, height);
		return img;
	}
	
	public static void main(String[] args) {
		JPanel jp = new JPanel();
		JLabel jl = new JLabel();
		List<Record> result = new ReportService().listThisMonthRecords();
		Image img = CharUtil.getImage(result, 400, 300);
		Icon icon = new ImageIcon(img);
		jl.setIcon(icon);
		jp.add(jl);
		GUIUtil.showPanel(jp);
	}
}
