package club.charleyking.util;

import javax.swing.*;

import club.charleyking.listener.FindActionListener;

import java.awt.GraphicsEnvironment;
import java.awt.Point;
public class Find extends JFrame{

	private static final long serialVersionUID = 1L;
	int startIndex=0;
    int select_start=-1;
    int select_end;
    public JLabel findLabel, replaceLabel;
    public JTextField findTextField, replaceTextField;
    public JButton findButton, findNextButton, replaceButton, replaceAllButton, cancelButton;  
    public JTextArea textArea;
    public FindActionListener listener;
    
    
    public Find(JTextArea textArea) { 
    	this.textArea = textArea;
        findLabel = new JLabel("Find:");
        replaceLabel = new JLabel("Replace:");
        findTextField = new JTextField(30);
        replaceTextField = new JTextField(30);
        findButton = new JButton("Find");
        findNextButton = new JButton("Find Next");
        replaceButton = new JButton("Replace");
        replaceAllButton = new JButton("Replace All");
        cancelButton = new JButton("Cancel");
        listener = new FindActionListener(this);
        
        // Set Layout NULL
        setLayout(null);
        
        // Set the width and height of the label
        int labWidth = 80;
        int labHeight = 20;
        
        // Adding labels
        findLabel.setBounds(10,10, labWidth, labHeight);
        add(findLabel);
        findTextField.setBounds(10+labWidth, 10, 120, 20);
        add(findTextField);
        replaceLabel.setBounds(10, 10+labHeight+10, labWidth, labHeight);
        add(replaceLabel);
        replaceTextField.setBounds(10+labWidth, 10+labHeight+10, 120, 20);
        add(replaceTextField);
         
        // Adding buttons
        findButton.setBounds(225, 6, 115, 20);
        add(findButton);
        findButton.addActionListener(listener);
        
        findNextButton.setBounds(225, 28, 115, 20);
        add(findNextButton);
        findNextButton.addActionListener(listener);
        
        replaceButton.setBounds(225, 50, 115, 20);
        add(replaceButton);
        replaceButton.addActionListener(listener);
        
        replaceAllButton.setBounds(225, 72, 115, 20);
        add(replaceAllButton);
        replaceAllButton.addActionListener(listener);
        
        cancelButton.setBounds(225, 94, 115, 20);
        add(cancelButton);
        cancelButton.addActionListener(listener);
          
        setSize(360,160);
        // Set window position
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        setLocation(center.x-360/2, center.y-160/2);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);  
    }
    
    public void find() {
        select_start = textArea.getText().toLowerCase().indexOf(findTextField.getText().toLowerCase());        
        if(select_start == -1)
        {
            startIndex = 0;
            JOptionPane.showMessageDialog(null, "Could not find \"" + findTextField.getText() + "\"!");
            return;
        }
        if(select_start == textArea.getText().toLowerCase().lastIndexOf(findTextField.getText().toLowerCase()))
        {
            startIndex = 0;
        }        
        int select_end = select_start + findTextField.getText().length();
        textArea.select(select_start, select_end);
    }
    
    public void findNext() {
        String selection = textArea.getSelectedText();
        try
        {
            selection.equals("");
        }
        catch(NullPointerException e)
        {
            selection = findTextField.getText();
            try
            {
                selection.equals("");
            }
            catch(NullPointerException e2)
            {
                selection = JOptionPane.showInputDialog("Find:");
                findTextField.setText(selection);
            }
        }
        try
        {
            int select_start = textArea.getText().toLowerCase().indexOf(selection.toLowerCase(), startIndex);
            int select_end = select_start+selection.length();
            textArea.select(select_start, select_end);
            startIndex = select_end+1;
        
            if(select_start == textArea.getText().toLowerCase().lastIndexOf(selection.toLowerCase()))
            {
                startIndex = 0;
            }
        }
        catch(NullPointerException e)
        {}
    }
    
    public void replace() {
        try
        {
            find();
            if (select_start != -1)
            textArea.replaceSelection(replaceTextField.getText());
        }
        catch(NullPointerException e)
        {
            System.out.print("Null Pointer Exception: "+e);
        }        
    }
    
    public void replaceAll() {      
        textArea.setText(textArea.getText().toLowerCase().replaceAll(findTextField.getText().toLowerCase(), replaceTextField.getText()));
    }

    
    
}
