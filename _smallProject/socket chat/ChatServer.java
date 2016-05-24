
import java.net.*;
import java.io.*;
import java.util.*;

public class ChatServer {
    static final int PORT = 4000;
    private byte[] buf = new byte[1000];
    private DatagramPacket packet = new DatagramPacket(buf,buf.length);
    private DatagramSocket sk;
 
    public ChatServer() {
        try {   
            sk = new DatagramSocket(PORT);
            System.out.println("Server started");
            while (true) {
                sk.receiveMessage(packet);
                String receiveMessage = "received from "+packet.getAddress()+", "+packet.getPort() + new String(packet.getData(),0,packet.getLength());
                System.out.println (receiveMessage);
                String outMessage ="";
                BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
                try{
                    outMessage = stdin.readLine();
                } catch (IOException ie) {
                    System.out.println("IO error");
                }
                String outString = "Server say:"+ outMessage;
                byte[] buf = outString.getBytes();
                DatagramPacket out = new DatagramPacket(buf,buf.length,packet.getAddress(),packet.getPort());
                sk.send(out);
            }
        } catch (SocketException e) {
            System.out.println("cannot open socket");
            System.exit(1);
        }catch(IOException e){
            System.out.println("Communication error");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args){
        new ChatServer();
    }
}
 


