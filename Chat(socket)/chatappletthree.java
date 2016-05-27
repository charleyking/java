import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;

public class chatappletthree implements  Runnable{  
    
    public static final  int PORT=1252;    
    String name, sex, line;  
    //Socket socket;   
    int jilu,enter=0;  
    DataInputStream inStream;                     
    DataOutputStream  outStream;  
    Thread thread;    
    //static Apanel a;  
    //static Bpanel b;  
    //static Cpanel c;
    //static ChatFrame  chatFrame;   
    private DatagramSocket socket;
    private InetAddress hostAddress;
    private byte[] buf = new byte[1000];
    private DatagramPacket packet = new DatagramPacket(buf,buf.length);  
        
    public static void main(String[] args) {
        new chatappletthree();
    }    

    public chatappletthree() {
        new ChatFrame();
        try{
            socket = new DatagramSocket();
            hostAddress = InetAddress.getByName("localhost");
            while(true){
                String outMessage ="";  
                BufferedReader stdin  = new BufferedReader(new InputStreamReader(System.in));
                try {
                    outMessage = stdin.readLine();
                } catch (IOException ie) {  
                    System.err.println("IO error!");   
                }
                if (outMessage.equals("bye")) break;
                String outString = "Client say: "+ outMessage;
                byte[] buf = outString.getBytes();
                DatagramPacket out = new DatagramPacket(buf,buf.length,hostAddress,4000);
                socket.send(out);
                socket.receive(packet);
                String received = new String(packet.getData(),0,packet.getLength()) + ", received from address: "+ packet.getAddress() + ", " + packet.getPort() + 
                ": ";
                System.out.println(received);
            }
        } catch (UnknownHostException e) {
            System.out.println("Can;t open socket");
            System.exit(1);
        } catch (SocketException e) {
            System.out.println("Can;t open socket");
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Communication error");
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Communication error");
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("ChatClient over");
    }
       
    /*public void init() {  
        chatFrame = new ChatFrame();     
        a = new Apanel();    
        b = new Bpanel();      
        c = new Cpanel();
        a.button1.addActionListener(this);
        a.button2.addActionListener(this);
        c.button.addActionListener(this);
        c.button2.addActionListener(this);
        c.button3.addActionListener(this);
        b.b2.list.addActionListener(this);
        chatFrame.add("North",a);   
        chatFrame.add("Center",b);  
        chatFrame.add("South",c);
        chatFrame.add("East",new  Label());
        chatFrame.add("West",new Label());
        jilu=0;
        c.msg_txt.setBackground(Color.white);
        b.chat_txt.setBackground(new Color(200,185,220));
        b.chat_txt.setFont(new Font("TimeRoman",Font.PLAIN,12));
        chatFrame.setBackground(new  Color(113,163,139));
        chatFrame.setLayout(new BorderLayout());
        chatFrame.setSize(800,550);
    }*/
        
    public void start() {  
        try {    
            socket = new Socket();
            //socket = new Socket(this.getCodeBase().getHost(),PORT);    
            //inStream = new DataInputStream(socket.getInputStream());
            outStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {   
            //this.showStatus(e.toString());
            say("error!");  
            System.exit(1);  
        }    
        say("hello");
        if (thread == null) {  
            thread = new Thread(this);
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();
        }
    }
       
    public void stop() {     
        try {   
            outStream.writeUTF("QUIT");      
        } catch (IOException e) {}   
    } 
       
    public void destroy() {  
        try {     
            socket.close();
        } catch (IOException e) {   
            //this.showStatus(e.toString());    
        }
        if ((thread!=null)&&thread.isAlive()) {  
            thread.yield();     
            thread=null;      
        }
    }
      
    public void run() {  
        String line;                  
        try {  
            while (true) {    
                line = inStream.readUTF();    
                if (line.startsWith("PEOPLE")) {   
                    String listString = line;
                    if (line. endsWith("*")) {    
                        listString = line.substring(0, (line.length()-1) );      
                    }  
                    //b.b2.list.add(listString.substring(6));    
                    if(!line. endsWith("*")) {   
                        //b.chat_txt.append(line.substring(6)+"sent this"+'\n');    
                    }  
                } else if (line.startsWith("QUIT")) {  
                    String  str = line.substring(10); 
                    try {  
                        for (int i=0,k=0;i<=120;i++) {  
                            //String s = b.b2.list.getItem(i); 
                            String s = "hello"; 
                            if (s.equals(str)) {      
                                k=i;           
                                //b.b2.list.remove(k);    
                                //b.chat_txt.append(line.substring(10)+"append"+'\n');
                            }    
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}
                } else if (line.startsWith("MSG")) { 
                    //b.chat_txt.append(line.substring(3)+'\n');    
                } else if (line.startsWith("hello")) {   
                    //b.chat_txt.append(line+'\n');      
                }
            }
        } catch (IOException e) {  
            say("something went wrong!");    
        } catch (NullPointerException e) {}
    }                                         
     
    /*public void actionPerformed(ActionEvent e) {  
        if (e.getSource()==a.button1) {
            name = new String(a.nameText.getText());    
            try {  
                for (int i=0;i<=120;i++) {  
                    if ((a.nameText.getText()!=null)&&((a.nameText.getText()+
                   "["+sex+"]").equals(b.b2.list.getItem(i))||a.nameText.getText().equals("hello"))) {  
                        jilu=1;    
                        name=null;   
                        break;     
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e3) {}
            if (jilu==0) {   
                try {     
                    outStream.writeUTF("PEOPLE"+a.nameText.getText()+"["+sex+"]");       
                    enter=1;    
                } catch (IOException  e1) {}   
            } else if (jilu==1) {  
                a.nameText.setText("wangjiahao"); 
                jilu=0;
            }
         else if (e.getSource()==a.button2) {  
            try {  
                outStream.writeUTF("QUIT");    
                enter=0;    
            } catch(IOException e1) { }
            b.b2.list.removeAll();    
        } else if(e.getSource()==c.button&&enter==1) {  
            if ((name!=null)) {    
                try { 
                    outStream.writeUTF("MSG"+name+"["+sex+"]"+"really"+":"+c.msg_txt.getText());
                    c.msg_txt.setText(null);     
                } catch (IOException  e1) { }
            }
        } else if (e.getSource()==b.b2.list&&enter==1) {  
            chatFrame.setVisible(true);
            chatFrame.text1.setText(((List)e.getSource()).getSelectedItem());
        } else if (e.getSource()==c.button2) {   
            b.chat_txt.setText(null);         
        } else if (e.getSource()==c.button3) {  
            try {   
                b.b2.list.removeAll();            
                outStream.writeUTF("newlist");      
            } catch (IOException  e1) { }
        }
    }  */
            
    public void say(String  msg) {  
        //b.chat_txt.append("message is "+msg+".\n");    
        System.out.println("say method");
    }
}
          
class ChatFrame extends JFrame /*implements ActionListener*/ {     
    JTextField text1,text2; 
    ChatFrame() {
        JPanel test = new Test();
        Apanel a = new Apanel();    
        Bpanel b = new Bpanel();      
        Cpanel c = new Cpanel();
        add(test);
        //setLayout(new BorderLayout());
        /*add("North",a);   
        add("Center",b);  
        add("South",c);
        add("East",new  JLabel("east"));
        add("West",new JLabel("west"));
        setLayout (new GridLayout(3,2));
        text1 = new JTextField(12);       
        text2 = new  JTextField(8);
        add(new JLabel("ok"));   
        add(text1);  
        add(new JLabel("stop"));
        add(text2);   */ // this will break the borderlayout
        //text1.setEditable(false);  
        //setBackground(new Color(182, 255, 147));
        setTitle("chat");
        setSize(550,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        /*a.button1.addActionListener(this);
        a.button2.addActionListener(this);
        c.button.addActionListener(this);
        c.button2.addActionListener(this);
        c.button3.addActionListener(this);
        b.b2.list.addActionListener(this);*/
        //
    }
    
    /*public void actionPerformed(ActionEvent  e) {
        if (e.getSource() == button1) {   
            System.out.println("actionPerformed"); 
        } else if (e.getSource() == button2) {   
            this.setVisible(false);     
        }
    }*/                                	
}

class Test extends JPanel implements ActionListener {
    JButton helloButton = new JButton("hello");
    JButton fadeButtoon = new JButton("fade");
    JTextField chatBox = new JTextField(10);
    Test() {
        add(helloButton);
        add(fadeButtoon);
        add(chatBox);
        helloButton.addActionListener(this);
        fadeButtoon.addActionListener(this);
        setBackground(new Color(182, 255, 147));
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == helloButton) {   
            System.out.println("hello charles"); 
        } else if (e.getSource() == fadeButtoon) {   
            this.setVisible(false);     
        }
    }
}

/*class Apanel extends JPanel implements ActionListener {   
    JTextField  nameText;    
    JButton  button1, button2;
    Bpanel bpanel; 
    String sex, name;
    int jilu = 0;
    int enter = 0;
    Apanel() {  
        nameText = new JTextField(10);  
        button1 = new JButton("button1");	
        button2 = new JButton("button2");   
        //box1 = new Checkbox("man",false,sex);
        //box2 = new Checkbox("woman",false,sex);
        add(new JLabel("sex"));    
        add(nameText);    
        add(button1);        
        add(button2);
        button1.addActionListener(this);
    }  	
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            name = new String(this.nameText.getText());    
            System.out.println("your name is: " + name);
            //try {  
                for (int i=0; i<=120; i++) {  
                    if ((name!=null) && ((name + "["+sex+"]").equals(bpanel.b2.list.getItem(i)) || name.equals("hello"))) {  
                        jilu=1;    
                        name = null;   
                        break;     
                    }
                }
            //} catch (ArrayIndexOutOfBoundsException e3) {

            //}
            if (jilu==0) {   
                //try {     
                    //outStream.writeUTF("PEOPLE"+name+"["+sex+"]");       
                    enter=1;    
                    System.out.println("jilu is 0");
                //} catch (IOException e1) {

                //}   
            } else if (jilu==1) {  
                this.nameText.setText("wangjiahao");  
                jilu=0;
            } else {
                System.out.println("nothing to do");
            }
        } else if (e.getSource() == button2) {
            //try {  
                //outStream.writeUTF("QUIT");    
                enter=0;    
            //} catch(IOException e1) { }
            bpanel.b2.list.removeAll();  
        }
    } 
}
 
class Bpanel  extends  JPanel {   
    JTextArea  chat_txt;   
    B2panel  b2;    
    Bpanel() {   
        chat_txt = new JTextArea(25,75);
        b2 = new  B2panel();
        chat_txt.setEditable(false);
        add(chat_txt);     
        add(b2);
        setLayout(new FlowLayout());
    } 	
} 
  
class B2panel  extends JPanel {   
    java.awt.List list; 
    B2panel() {  
        try {  
            list = new java.awt.List(25,false);    
        } catch (NullPointerException e) {}
        setLayout(new BorderLayout());
        add("Center",list);     
        add("North",new JLabel("north"));
        add("East",new  JLabel("east"));     	
        add("South",new JLabel("south"));
    }
}
   
class Cpanel  extends  JPanel {  
    JTextField  msg_txt;   
    JButton  button,button2,button3;  
    Cpanel() {  
        msg_txt = new JTextField(44);    
        button = new   JButton("button1");
        button2 = new  JButton("button2");
        button3 = new  JButton("button3");
        add(new JLabel("button group"));
        add(msg_txt);
        add(button);  
        add(button2);   
        add(button3);
        setLayout(new  FlowLayout());
    }   
} 
*/    

             
     
               
             
                  
                                 
        
        
           
          