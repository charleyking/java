import  java.net.*;
import  java.io.*;
import  java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class chatappletthree implements  Runnable,ActionListener {  
    
    public  static final  int PORT=1252;    
    static  String  name, sex, line;  
    Socket  socket;   
    static int   jilu,enter=0;  
    static DataInputStream inStream;                     
    static DataOutputStream  outStream;  
    Thread thread;    
    static Apanel a;  
    static Bpanel  b;  
    static Cpanel c;
    static ChatFrame  chatFrame;     
        
    public static void main(String[] args) {
        new chatappletthree();
        new ChatFrame();
    }    
       
    public  void init() {  
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
    }
        
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
      
    public  void run() {  
        String line;                  
        try {  
            while (true) {    
                line = inStream.readUTF();    
                if (line.startsWith("PEOPLE")) {   
                    String listString = line;
                    if (line. endsWith("*")) {    
                        listString = line.substring(0, (line.length()-1) );      
                    }  
                    b.b2.list.add(listString.substring(6));    
                    if(!line. endsWith("*")) {   
                        b.chat_txt.append(line.substring(6)+"sent this"+'\n');    
                    }  
                } else if (line.startsWith("QUIT")) {  
                    String  str = line.substring(10); 
                    try {  
                        for (int i=0,k=0;i<=120;i++) {  
                            String s = b.b2.list.getItem(i);  
                            if (s.equals(str)) {      
                                k=i;           
                                b.b2.list.remove(k);    
                                b.chat_txt.append(line.substring(10)+"append"+'\n');
                            }    
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}
                } else if (line.startsWith("MSG")) { 
                    b.chat_txt.append(line.substring(3)+'\n');    
                } else if (line.startsWith("hello")) {   
                    b.chat_txt.append(line+'\n');      
                }
            }
        } catch (IOException e) {  
            say("something went wrong!");    
        } catch (NullPointerException e) {}
    }                                         
     
    public void actionPerformed(ActionEvent e) {  
        if (e.getSource()==a.button1) {
            name = new String(a.name_txt.getText());    
            try {  
                for (int i=0;i<=120;i++) {  
                    if ((a.name_txt.getText()!=null)&&((a.name_txt.getText()+
                   "["+sex+"]").equals(b.b2.list.getItem(i))||a.name_txt.getText().equals("hello"))) {  
                        jilu=1;    
                        name=null;   
                        break;     
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e3) {}
            if (jilu==0) {   
                try {     
                    outStream.writeUTF("PEOPLE"+a.name_txt.getText()+"["+sex+"]");       
                    enter=1;    
                } catch (IOException  e1) {}   
            } else if (jilu==1) {  
                a.name_txt.setText("wangjiahao");  }
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
    }  
            
    public void say(String  msg) {  
        b.chat_txt.append("message is "+msg+".\n");    
    }
}
          
class ChatFrame extends Frame implements ActionListener {     
    TextField text1,text2;     
    Button button1,button2;
    ChatFrame() {
        super("chat app");
        setLayout (new GridLayout(3,2));
        text1 = new TextField(12);       
        text2 = new  TextField(8);
        button1 = new Button("hello");    
        button2=new Button("vade");
        add(new Label("ok"));   
        add(text1);  
        add(new Label("stop"));
        add(text2);   
        add(button2); 
        add(button1);
        setSize(400,190);  
        text1.setEditable(false);   
        setVisible(true);
        button1.addActionListener(this);     
        button2.addActionListener(this);
        setBackground(Color.pink);
        addWindowListener(new WindowAdapter() {    
            public  void windowClosing(WindowEvent e) {  
                setVisible(false);  
                System.exit(0);   
            }
        });
    }
    
    public void actionPerformed(ActionEvent  e) {
        if (e.getSource() == button1) {   
            System.out.println("actionPerformed"); 
        } else if (e.getSource() == button2) {   
            this.setVisible(false);     
        }
    }                                   	
}
 
class Apanel  extends Panel {   
    TextField  name_txt;    
    Button  button1, button2;
    Checkbox  box1, box2, box3;   
    Apanel() {  
        name_txt = new TextField(10);  
        button1 = new Button("A");	
        button2 = new Button("B");   
        //box1 = new Checkbox("man",false,sex);
        //box2 = new Checkbox("woman",false,sex);
        add(new Label("sex"));    
        add(name_txt);   
        add(box1);     
        add(box2);    
        add(button1);        
        add(button2);
        setLayout(new FlowLayout()); 
     }  	
  }
 
class Bpanel  extends  Panel {   
    TextArea  chat_txt;   
    B2panel  b2;    
    Bpanel() {   
        chat_txt = new TextArea(25,75);
        b2 = new  B2panel();
        chat_txt.setEditable(false);
        add(chat_txt);     
        add(b2);
        setLayout(new FlowLayout());
    } 	
} 
  
class B2panel  extends Panel {   
    java.awt.List list; 
    B2panel() {  
        try {  
            list = new java.awt.List(25,false);    
        } catch (NullPointerException e) {}
        add("Center",list);     
        add("North",new Label("north"));
        add("East",new  Label());     	
        add("South",new Label("south"));
        setLayout(new BorderLayout());
    }
}
   
class Cpanel  extends  Panel {  
    TextField  msg_txt;   
    Button  button,button2,button3;  
    Cpanel() {  
        msg_txt = new TextField(44);    
        button = new   Button("button1");
        button2 = new  Button("button2");
        button3 = new  Button("button3");
        add(new Label("button group"));
        add(msg_txt);
        add(button);  
        add(button2);   
        add(button3);
        setLayout(new  FlowLayout());
    }   
}
      

             
     
               
             
                  
                                 
        
        
           
          