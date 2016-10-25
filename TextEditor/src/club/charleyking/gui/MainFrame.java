package club.charleyking.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.border.Border;

import club.charleyking.listener.ButtonAndItemListener;

public class MainFrame extends JFrame {
	public JPanel textPanel;
	public JMenuBar menubar;
	public JMenu menuFile, menuEdit, menuSearch, menuAbout;
	public JMenuItem itemNewFile, itemOpenFile, itemSaveFile, itemClose, 
	itemCut, itemCopy, itemPaste, itemClearFile, itemSelectAll, itemQuickFind, itemAboutMe, itemAboutSoftware;
	public JTextArea textArea;
	public JToolBar toolBar;
	public JButton newButton, openButton, saveButton, closeButton, quickButton, aboutmeButton, aboutButton, 
	spaceOneButton, spaceTwoButton;
	public ButtonAndItemListener listener;
	private final ImageIcon iconNewFile = new ImageIcon("icons/new.png");
	private final ImageIcon iconOpenFile = new ImageIcon("icons/open.png");
	private final ImageIcon iconSaveFile = new ImageIcon("icons/save.png");
	private final ImageIcon iconClose = new ImageIcon("icons/close.png");
	private final ImageIcon iconCut = new ImageIcon("icons/cut.png");
	private final ImageIcon iconCopy = new ImageIcon("icons/copy.png");
	private final ImageIcon iconPaste = new ImageIcon("icons/paste.png");
	private final ImageIcon iconSearch = new ImageIcon("icons/search.png");
	private final ImageIcon iconAboutMe = new ImageIcon("icons/about_me.png");
	private final ImageIcon iconAbout = new ImageIcon("icons/about.png");
	
	public MainFrame() {
		// set the menus
		menuFile = new JMenu("File");
		menuEdit = new JMenu("Edit");
		menuSearch = new JMenu("Search");
		menuAbout = new JMenu("About");
		
		// set the menuItems
		itemNewFile = new JMenuItem("New File", iconNewFile);
		itemOpenFile = new JMenuItem("Open File", iconOpenFile);
		itemSaveFile = new JMenuItem("Save File", iconSaveFile);
		itemClose = new JMenuItem("Close", iconClose);
		itemCut = new JMenuItem("Cut", iconCut);
		itemCopy = new JMenuItem("Copy", iconCopy);
		itemPaste = new JMenuItem("Paste", iconPaste);
		itemClearFile = new JMenuItem("ClearFile", iconSearch);
		itemSelectAll = new JMenuItem("Select All", iconSearch);
		itemQuickFind = new JMenuItem("QuickFind", iconSearch);
		itemAboutMe = new JMenuItem("AboutMe", iconAboutMe);
		itemAboutSoftware = new JMenuItem("AboutSoftware", iconAbout);
		
		// add listener to menuItems
		listener = new ButtonAndItemListener(this);
		itemNewFile.addActionListener(listener);
		itemOpenFile.addActionListener(listener);
		
		// add the menuItems to menus
		menuFile.add(itemNewFile);
		menuFile.add(itemOpenFile);
		menuFile.add(itemSaveFile);
		menuFile.add(itemClose);
		menuEdit.add(itemCut);
		menuEdit.add(itemCopy);
		menuEdit.add(itemPaste);
		menuEdit.add(itemClearFile);
		menuSearch.add(itemSelectAll);
		menuSearch.add(itemQuickFind);
		menuAbout.add(itemAboutMe);
		menuAbout.add(itemAboutSoftware);
		// set the menuBar into frame
		menubar = new JMenuBar();
		menubar.add(menuFile);
		menubar.add(menuEdit);
		menubar.add(menuSearch);
		menubar.add(menuAbout);
		this.setJMenuBar(menubar);
		
		// create buttons on tool bar
		newButton = new JButton(iconNewFile);
		openButton = new JButton(iconOpenFile);
		saveButton = new JButton(iconSaveFile);
		closeButton = new JButton(iconClose);
		quickButton = new JButton(iconSearch);
		aboutmeButton = new JButton(iconAboutMe);
		aboutButton = new JButton(iconAbout);
		Border emptyBorder = BorderFactory.createEmptyBorder(0,0,0,20);
		spaceOneButton = new JButton();
		spaceTwoButton = new JButton();
		spaceOneButton.setBorder(emptyBorder);
		spaceTwoButton.setBorder(emptyBorder);
		
		// set button tool tip text
		newButton.setToolTipText("new");
		openButton.setToolTipText("open");
		saveButton.setToolTipText("save");
		closeButton.setToolTipText("close");
		quickButton.setToolTipText("search");
		aboutmeButton.setToolTipText("about me");
		aboutButton.setToolTipText("about");
		
		
		// add button listener
		newButton.addActionListener(listener);
		openButton.addActionListener(listener);
		
		// set tool bar
		toolBar = new JToolBar();
		toolBar.add(newButton);
		toolBar.addSeparator();
		toolBar.add(openButton);
		toolBar.addSeparator();
		toolBar.add(saveButton);
		toolBar.addSeparator();
		toolBar.add(closeButton);
		toolBar.addSeparator();
		toolBar.add(spaceOneButton);
		toolBar.addSeparator();
		toolBar.add(quickButton);
		toolBar.addSeparator();
		toolBar.add(spaceTwoButton);
		toolBar.addSeparator();
		toolBar.add(aboutmeButton);
		toolBar.addSeparator();
		toolBar.add(aboutButton);
		this.add(toolBar, BorderLayout.NORTH);
		
		// set text area
		textArea = new JTextArea("", 0, 0);
		textArea.setFont(new Font("serif", Font.BOLD, 14));
		textArea.setTabSize(2);
		/*textPanel = new JPanel();
		textPanel.add(textArea);*/
		this.add(textArea);
		
		
		initFrame();
	}
	
	public void initFrame() {
		this.setSize(700, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
