import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class ChatServerFrame extends JFrame {
	private JScrollPane scrollPane;
	private JTextArea textArea_info;
	private ServerSocket server; 
	private Socket socket; 
	private BufferedReader reader;
	private Hashtable<String, Socket> map = new Hashtable<String, Socket>();

	public ChatServerFrame() {
		super();
		/*addWindowListener(new WindowAdapter() {
			public void windowIconified(final WindowEvent e) {
				setVisible(false);
			}
		});*/
		//setBounds(100, 100, 385, 266);
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		textArea_info = new JTextArea();
		scrollPane.setViewportView(textArea_info);
		setTitle("chat room server");
		setSize(550, 600);
		setState(Frame.NORMAL);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// tray
		/*if (SystemTray.isSupported()){ 
			URL url=ChatServerFrame.class.getResource("1.jpg"); 
			ImageIcon icon = new ImageIcon(url); 
			Image image=icon.getImage(); 
			TrayIcon trayIcon=new TrayIcon(image);
			trayIcon.addMouseListener(new MouseAdapter(){ 
				public void mouseClicked(MouseEvent e){ 
					if (e.getClickCount()==2){ 
						showFrame(); 
					}
				}
			});
			trayIcon.setToolTip("system tray");
			PopupMenu popupMenu=new PopupMenu(); 
			MenuItem exit=new MenuItem("exit"); 
			exit.addActionListener(new ActionListener() { 
				public void actionPerformed(final ActionEvent arg0) {
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
		}*/
	}

	public void createSocket() {
		try {
			server = new ServerSocket(1982);
			textArea_info.append("waiting new client to connect\n");
			while (true) {
				socket = server.accept();
				textArea_info.append("client connect successful " + socket + "\n");
				new ServerThread(socket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class ServerThread extends Thread {
		Socket socket;
		public ServerThread(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			textArea_info.append("I'm in run method now\n");
			try {
				ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				textArea_info.append(reader.readLine());
				while (true) {
					Vector v = null;
					try {
						v = (Vector) inputStream.readObject();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					if (v != null && v.size() > 0) {
						for (int i = 0; i < v.size(); i++) {
							String info = (String) v.get(i); // get message
							String key = "";
							if (info.startsWith("user: ")) { // add login user to client list
								System.out.println("received info is " + info);
								key = info.substring(3, info.length()); // get user name as key
								map.put(key, socket);
								Set<String> set = map.keySet();
								Iterator<String> keyIt = set.iterator();
								while (keyIt.hasNext()) {
									String receiveKey = keyIt.next(); // get message as key
									Socket s = map.get(receiveKey);
									PrintWriter out = new PrintWriter(s.getOutputStream(), true);
									Iterator<String> keyIt1 = set.iterator();
									while (keyIt1.hasNext()) {
										String receiveKey1 = keyIt1.next(); // add user list to client
										out.println(receiveKey1);
										out.flush();
									}
								}
							} else if (info.startsWith("exit: ")) {
								key = info.substring(3);// get exit user as key
								map.remove(key);
								Set<String> set = map.keySet();
								Iterator<String> keyIt = set.iterator();
								while (keyIt.hasNext()) {
									String receiveKey = keyIt.next();
									Socket s = map.get(receiveKey);
									PrintWriter out = new PrintWriter(s.getOutputStream(), true);
									out.println("exit: " + key);
									out.flush();
								}
							} else { // resent message
								key = info.substring(info.indexOf(": resent to : ") + 5,info.indexOf(": message is: ")); // get receive user as key
								String sendUser = info.substring(0, info.indexOf(": send to : ")); // get send user as key
								Set<String> set = map.keySet();
								Iterator<String> keyIt = set.iterator();
								while (keyIt.hasNext()) {
									String receiveKey = keyIt.next();
									if (key.equals(receiveKey) && !sendUser.equals(receiveKey)) { // same as receive user, not send user
										Socket s = map.get(receiveKey);
										PrintWriter out = new PrintWriter(s.getOutputStream(), true);
										out.println("MSG:" + info);
										out.flush();
									}
								}
							}
						}
					}
				}
			} catch (IOException e) {
				textArea_info.append(socket + "already exited \n");
			}
		}
	}

	/*public void showFrame(){
		this.setVisible(true); 
		this.setState(Frame.NORMAL);
	}*/

	public static void main(String args[]) {
		ChatServerFrame frame = new ChatServerFrame();
		frame.createSocket();
	}
}