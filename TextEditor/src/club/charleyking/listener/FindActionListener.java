package club.charleyking.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import club.charleyking.util.Find;

public class FindActionListener implements ActionListener{
	public Find find;
	
	public FindActionListener(Find find) {
		this.find = find;
	}
	
	public void actionPerformed(ActionEvent e) {
        if(e.getSource() == find.findButton)
        {
           find.find();
        }
        else if(e.getSource() == find.findNextButton)
        {
           find.findNext();
        }
        else if(e.getSource() == find.replaceButton)
        {
            find.replace();
        }
        else if(e.getSource() == find.replaceAllButton)
        {
           find.replaceAll();
        }
        else if(e.getSource() == find.cancelButton)
        {
           find.setVisible(false);
        }
   }
}
