import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.util.*;
import javax.swing.*;

public class ChatClientFrame extends JFrame {
	private JPanel topPanel, bottomPanel;
	private JLabel label_send, lable_user;
	private JButton button_send, button_login, button_exit;
	private JTextField textField_newUser, textField_send;
	private JScrollPane rightScrollPane, leftScrollPane;
	private JSplitPane splitPane;
	private JList user_list;
	private JTextArea textArea_info;
	//private JTextField textField_send;
	private ObjectOutputStream out;
	private boolean loginFlag = false;
	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;

	public ChatClientFrame() {
		super();
		//setBounds(100, 100, 385, 288);
		// bottom panel
		bottomPanel = new JPanel();
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		label_send = new JLabel();
		label_send.setText("Please input the chat content: ");
		bottomPanel.add(label_send);
		textField_send = new JTextField();
		textField_send.setPreferredSize(new Dimension(180, 25));
		bottomPanel.add(textField_send);
		button_send = new JButton();
		button_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send();
			}
		});
		button_send.setText("send");
		bottomPanel.add(button_send);
		// center panel -- this is a split panel
		splitPane = new JSplitPane();
		splitPane.setDividerLocation(100);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		// right panel -- this is a scroll panel with a textArea in it
		rightScrollPane = new JScrollPane();
		splitPane.setRightComponent(rightScrollPane);
		textArea_info = new JTextArea();
		textArea_info.setFont(new Font("", Font.BOLD, 14));
		rightScrollPane.setViewportView(textArea_info);
		// left panel -- this is a scroll panel with a list of login users
		leftScrollPane = new JScrollPane();
		splitPane.setLeftComponent(leftScrollPane);
		user_list = new JList();
		//user_list.setModel(new DefaultComboBoxModel(new String[] { "" }));
		leftScrollPane.setViewportView(user_list);
		// top panel 
		topPanel = new JPanel();
		getContentPane().add(topPanel, BorderLayout.NORTH);
		lable_user = new JLabel();
		lable_user.setText("user name: ");
		topPanel.add(lable_user);
		textField_newUser = new JTextField();
		textField_newUser.setPreferredSize(new Dimension(140, 25));
		topPanel.add(textField_newUser);
		button_login = new JButton();
		button_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (loginFlag) { // already login
					JOptionPane.showMessageDialog(null, "you've already login in!");
					return;
				}
				String userName = textField_newUser.getText().trim(); // get user name
				Vector v = new Vector(); // save the user data
				v.addElement("user: " + userName);// add login user
				/*try {
					out.writeObject(v); // send user data to server
					out.flush();
				} catch (IOException ex) {
					ex.printStackTrace();
					System.out.println("can't send login message to server");
				}
				*/
				textField_newUser.setEnabled(false); // disable the user text field
				loginFlag = true; // already login
			}
		});
		button_login.setText("login");
		topPanel.add(button_login);
		button_exit = new JButton();
		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String exitUser = textField_newUser.getText().trim();
				Vector v = new Vector();
				v.addElement("exit: " + exitUser);
				/*try {
					out.writeObject(v);
					out.flush();
				} catch (IOException ex) {
					ex.printStackTrace();
					System.out.println("can't send exit message to server");
				}*/
				System.exit(0);
			}
		});
		button_exit.setText("eixt");
		topPanel.add(button_exit);

		setSize(550, 600);
		setTitle("Chat Room");
		setVisible(true);
		setState(Frame.NORMAL);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*// tray
		if (SystemTray.isSupported()){  // judge if support system tray
			URL url=ChatClientFrame.class.getResource("1.jpg");   // get url of image
			ImageIcon icon = new ImageIcon(url); 
			Image image=icon.getImage(); 
			TrayIcon trayIcon=new TrayIcon(image); // create tray icon
			trayIcon.addMouseListener(new MouseAdapter(){ 
				public void mouseClicked(MouseEvent e){ 
					if (e.getClickCount()==2){  // if double clicked mouse
						//showFrame(); 
					}
				}
			});
			trayIcon.setToolTip("system tray"); 
			PopupMenu popupMenu=new PopupMenu(); 
			MenuItem exit=new MenuItem("exit"); 
			exit.addActionListener(new ActionListener() { 
				public void actionPerformed(final ActionEvent arg0) {
					String exitUser = textField_newUser.getText().trim();
					Vector v = new Vector();
					//v.add("exit: " + exitUser);
					try {
						out.writeObject(v);
						out.flush();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					System.exit(0); 
				}
			});
			popupMenu.add(exit); 
			trayIcon.setPopupMenu(popupMenu); 
			SystemTray systemTray=SystemTray.getSystemTray(); 
			try{
				systemTray.add(trayIcon); 
			}catch(Exception e){
				e.printStackTrace();
			}
		} */
	} 

	/*public void showFrame(){
		this.setVisible(true); // show frame
		this.setState(Frame.NORMAL);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}*/


	public void createClientSocket() {
		try {
			socket = new Socket("localhost", 1982); // create a socket
			out = new ObjectOutputStream(socket.getOutputStream()); // create an output stream
			writer = new PrintWriter(socket.getOutputStream(),true);
			writer.println("hello");
			new ClientThread(socket).start(); // create a thread and start it
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class ClientThread extends Thread {
		Socket socket;
		public ClientThread(Socket socket) {
			this.socket = socket;
		}
		public void run() {
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // create an input stream
				//DefaultComboBoxModel model = (DefaultComboBoxModel) user_list.getModel(); // get list model
				while (true) {
					String info = reader.readLine().trim(); // read message
					if (!info.startsWith("message:")) {
						if (info.startsWith("exit: ")) { // start with exit message
							System.out.println("info start with exit");
							//model.removeElement(info.substring(3)); // remove user from list
						} else { 
							System.out.println("info no start with exit");
							/*boolean itemFlag = false;
							for (int i = 0; i < model.getSize(); i++) {
								if (info.equals((String) model.getElementAt(i))) {
									itemFlag = true;
									break;
								}
							}
							if (!itemFlag) {
								//model.addElement(info);
								System.out.println("add login user to user list");
							} */
						}
					} else { // if received message, than show it at text area
						DateFormat df = DateFormat.getDateInstance();
						String dateString = df.format(new Date()); 
						df = DateFormat.getTimeInstance(DateFormat.MEDIUM);
						String timeString = df.format(new Date()); 
						String sendUser = info.substring(4,info.indexOf(": send to: "));
						String receiveInfo = info.substring(info.indexOf(": received message is: ")+6);
						textArea_info.append(" "+sendUser + " " +dateString+" "+timeString+"\n "+receiveInfo+"\n"); // show in the text area
						textArea_info.setSelectionStart(textArea_info.getText().length()-1);
						textArea_info.setSelectionEnd(textArea_info.getText().length());
						textField_send.requestFocus();
					}
				}
			} catch (IOException e) {
				System.out.println("IOException in run method");
			}
		}
	}

	private void send() {
		/*if (!loginFlag) {
			JOptionPane.showMessageDialog(null, "please login first");
			return;
		}*/
		String sendUserName = textField_newUser.getText().trim();
		String sendText = textField_send.getText();
		if (sendText.equals("")) {
			return;
		}
		Vector<String> v = new Vector<String>();
		/*java.util.List receiveUserNamesList = user_list.getSelectedValuesList(); // get select user 
		Object[] receiveUserNames = receiveUserNamesList.toArray();
		if (receiveUserNames.length <= 0) {
			return;
		}
		for (int i = 0; i < receiveUserNames.length; i++) {
			String message = sendUserName + " send to " + (String) receiveUserNames[i]+ ": " + sendText; // identify the message
			v.add(message);
		}
		try {
			out.writeObject(v);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		DateFormat df = DateFormat.getDateInstance(); // get dataformat instance
		String dateString = df.format(new Date()); // format to date
		df = DateFormat.getTimeInstance(DateFormat.MEDIUM);
		String timeString = df.format(new Date()); // format to time
		//String sendUser = textField_newUser.getText().trim();
		//String receiveInfo = textField_send.getText().trim();
		textArea_info.append(sendUserName + " " +dateString+" "+ timeString + "\n" + sendText + "\n");
		textField_send.setText(null);
		textArea_info.setSelectionStart(textArea_info.getText().length()-1);
		textArea_info.setSelectionEnd(textArea_info.getText().length());
		textField_send.requestFocus();
	}

	/*public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatClientFrame frame = new ChatClientFrame();
					//frame.showFrame();
					frame.createClientSocket();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	public static void main(String[] args) {
		ChatClientFrame frame = new ChatClientFrame();
		frame.createClientSocket();
	}
}