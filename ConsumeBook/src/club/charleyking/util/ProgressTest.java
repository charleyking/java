package club.charleyking.util;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

public class ProgressTest {
	public static void main(String[] args) {
		GUIUtil.useLNF();
		JPanel jp = new JPanel();
		CircleProgressBar cpb = new CircleProgressBar();
		cpb.setBackgroundColor(ColorUtil.blueColor);
		cpb.setProgress(0);
		
		JButton jb = new JButton("click");
		jp.setLayout(new BorderLayout());
		jp.add(cpb, BorderLayout.CENTER);
		jp.add(jb, BorderLayout.SOUTH);
		GUIUtil.showPanel(jp);
		
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SwingWorker() {
					@Override
					protected Object doInBackground() throws Exception {
						for (int i = 0; i < 100; i++) {
							cpb.setProgress(i + 1);
							cpb.setForegroundColor(ColorUtil.getByPercentage(i + 1));
							try {
								Thread.sleep(100);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
						return null;
					}
				}.execute();
			}
		});
	}
}
