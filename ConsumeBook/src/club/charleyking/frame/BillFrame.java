package club.charleyking.frame;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

public class BillFrame {
	public static void main(String args[]) {
		JFrame jf = new JFrame();
		jf.setTitle("bill book");
		jf.setVisible(true);
		jf.setSize(500, 500);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JToolBar jtb = new JToolBar();
		JButton btn_spend = new JButton("spend");
		JButton btn_record = new JButton("record");
		JButton btn_category = new JButton("category");
		JButton btn_report = new JButton("report");
		JButton btn_config = new JButton("config");
		JButton btn_backup = new JButton("backup");
		JButton btn_recover = new JButton("recover");
		
		jtb.add(btn_spend);
		jtb.add(btn_record);
		jtb.add(btn_category);
		jtb.add(btn_report);
		jtb.add(btn_config);
		jtb.add(btn_backup);
		jtb.add(btn_recover);
		jf.setLayout(new BorderLayout());
		jf.add(jtb, BorderLayout.NORTH);
		
		jf.add(new Panel(),BorderLayout.CENTER);
		
		btn_spend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 
            }
        });
		btn_record.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 
            }
        });
		btn_category.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 
            }
        });
		btn_report.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 
            }
        });
		btn_config.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 
            }
        });
		btn_backup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 
            }
        });
		btn_recover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 
            }
        });
	}
	
}
